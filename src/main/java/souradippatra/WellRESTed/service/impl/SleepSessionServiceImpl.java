package souradippatra.WellRESTed.service.impl;

import souradippatra.WellRESTed.client.SleepRecommendationClient;
import souradippatra.WellRESTed.domain.SleepSession;
import souradippatra.WellRESTed.dto.SleepSessionDto;
import souradippatra.WellRESTed.mapper.SleepSessionMapper;
import souradippatra.WellRESTed.repository.SleepSessionRepository;
import souradippatra.WellRESTed.service.SleepSessionService;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class SleepSessionServiceImpl implements SleepSessionService {


    private final SleepSessionRepository repo;
    private final SleepSessionMapper mapper;
    private final SleepRecommendationClient recommendationClient;


    public SleepSessionServiceImpl(SleepSessionRepository repo, SleepSessionMapper mapper, SleepRecommendationClient recommendationClient) {
        this.repo = repo;
        this.mapper = mapper;
        this.recommendationClient = recommendationClient;
    }


    @Override
    public SleepSessionDto create(SleepSessionDto dto) {
        SleepSession entity = mapper.toEntity(dto);
        SleepSession saved = repo.save(entity);
        SleepSessionDto result = mapper.toDto(saved);

        // Fetch recommendation
        if (saved.getSleepQuality() != null) {
            SleepRecommendationClient.SleepRecommendationDto rec = recommendationClient.getRecommendation(saved.getSleepQuality());
            result.setRecommendation(rec.recommendation());
            result.setAdvice(rec.advice());
        }   
        return mapper.toDto(saved);
    }


    @Override
    @Transactional(readOnly = true)
    public SleepSessionDto getById(Long id) {
        SleepSession entity = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("SleepSession not found: " + id));
        return mapper.toDto(entity);
    }

    @Override
    public Page<SleepSessionDto> getAllWithRecommendations(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("startTime").descending());
        Page<SleepSession> new_page = repo.findAll(pageable);
        return new_page.map(mapper::toDto);
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