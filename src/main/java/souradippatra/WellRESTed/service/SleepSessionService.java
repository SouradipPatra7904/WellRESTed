package souradippatra.WellRESTed.service;

import souradippatra.WellRESTed.dto.SleepSessionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;


public interface SleepSessionService {
    SleepSessionDto create(SleepSessionDto dto);
    SleepSessionDto getById(Long id);
    SleepSessionDto replace(Long id, SleepSessionDto dto);
    SleepSessionDto patch(Long id, SleepSessionDto partial);
    void delete(Long id);


    Page < SleepSessionDto > list(Long userId, Pageable pageable);
    List < SleepSessionDto > seek(Long userId, Long lastId, int size);
    Page<SleepSessionDto> getAllWithRecommendations(int page, int size);
}