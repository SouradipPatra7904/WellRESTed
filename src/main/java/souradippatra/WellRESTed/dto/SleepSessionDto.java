package souradippatra.WellRESTed.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SleepSessionDto {
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private Instant startTime;

    @NotNull
    private Instant endTime;

    private Integer sleepQuality;
    private String notes;

    private Instant createdAt;
    private Instant updatedAt;

    // New field for recommendation
    private String recommendation;
    private String advice;
}
