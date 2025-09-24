package souradippatra.WellRESTed.service.impl;

import souradippatra.WellRESTed.domain.SleepSession;
import souradippatra.WellRESTed.dto.SleepSessionDto;
import souradippatra.WellRESTed.mapper.SleepSessionMapper;
import souradippatra.WellRESTed.repository.SleepSessionRepository;
import souradippatra.WellRESTed.service.SleepSessionService;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class SleepSessionServiceImpl implements SleepSessionService {


    private final SleepSessionRepository repo;
    private final SleepSessionMapper mapper;


    public SleepSessionServiceImpl(SleepSessionRepository repo, SleepSessionMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }


    @Override
    public SleepSessionDto create(SleepSessionDto dto) {
        SleepSession entity = mapper.toEntity(dto);
        SleepSession saved = repo.save(entity);
        return mapper.toDto(saved);
    }


    @Override
    @Transactional(readOnly = true)
    public SleepSessionDto getById(Long id) {
        return repo.findById(id).map(mapper::toDto)
            .orElseThrow(() -> new EntityNotFoundException("SleepSession not found: " + id));
    }


    @Override
    public SleepSessionDto replace(Long id, SleepSessionDto dto) {
        SleepSession existing = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("SleepSession not found: " + id));
        // Replace fields fully (except id and timestamps)
        existing.setUserId(dto.getUserId());
        existing.setStartTime(dto.getStartTime());
        existing.setEndTime(dto.getEndTime());
        existing.setSleepQuality(dto.getSleepQuality());
        existing.setNotes(dto.getNotes());
        SleepSession saved = repo.save(existing);
        return mapper.toDto(saved);
    }


    @Override
    public SleepSessionDto patch(Long id, SleepSessionDto partial) {
        SleepSession existing = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("SleepSession not found: " + id));
        mapper.applyPatch(partial, existing);
        SleepSession saved = repo.save(existing);
        return mapper.toDto(saved);
    }


    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) throw new EntityNotFoundException("SleepSession not found: " + id);
        repo.deleteById(id);
    }


    @Override
    @Transactional(readOnly = true)
    public Page < SleepSessionDto > list(Long userId, Pageable pageable) {
        Page < SleepSession > page = (userId == null) ? repo.findAll(pageable) : repo.findByUserId(userId, pageable);
        List < SleepSessionDto > dtos = page.stream().map(mapper::toDto).collect(Collectors.toList());
        return new PageImpl < > (dtos, pageable, page.getTotalElements());
    }


    @Override
    @Transactional(readOnly = true)
    public List < SleepSessionDto > seek(Long userId, Long lastId, int size) {
        Pageable p = Pageable.ofSize(size);
        List < SleepSession > results = repo.seekByUserIdAfter(userId, lastId, p);
        return results.stream().map(mapper::toDto).collect(Collectors.toList());
    }
}