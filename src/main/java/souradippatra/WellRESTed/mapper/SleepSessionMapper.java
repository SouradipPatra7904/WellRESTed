package souradippatra.WellRESTed.mapper;

import souradippatra.WellRESTed.domain.SleepSession;
import souradippatra.WellRESTed.dto.SleepSessionDto;
import org.springframework.stereotype.Component;


@Component
public class SleepSessionMapper {


    public SleepSessionDto toDto(SleepSession e) {
        if (e == null) return null;
        return SleepSessionDto.builder()
            .id(e.getId())
            .userId(e.getUserId())
            .startTime(e.getStartTime())
            .endTime(e.getEndTime())
            .sleepQuality(e.getSleepQuality())
            .notes(e.getNotes())
            .createdAt(e.getCreatedAt())
            .updatedAt(e.getUpdatedAt())
            .build();
    }


    public SleepSession toEntity(SleepSessionDto d) {
        if (d == null) return null;
        return SleepSession.builder()
            .id(d.getId())
            .userId(d.getUserId())
            .startTime(d.getStartTime())
            .endTime(d.getEndTime())
            .sleepQuality(d.getSleepQuality())
            .notes(d.getNotes())
            .createdAt(d.getCreatedAt())
            .updatedAt(d.getUpdatedAt())
            .build();
    }


    // apply partial updates from dto -> entity
    public void applyPatch(SleepSessionDto dto, SleepSession entity) {
        if (dto.getStartTime() != null) entity.setStartTime(dto.getStartTime());
        if (dto.getEndTime() != null) entity.setEndTime(dto.getEndTime());
        if (dto.getSleepQuality() != null) entity.setSleepQuality(dto.getSleepQuality());
        if (dto.getNotes() != null) entity.setNotes(dto.getNotes());
        if (dto.getUserId() != null) entity.setUserId(dto.getUserId());
    }
}