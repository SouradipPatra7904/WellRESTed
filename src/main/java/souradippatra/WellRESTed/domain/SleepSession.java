package souradippatra.WellRESTed.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;


/**
* Core domain entity for a single recorded sleep session.
*/
@Entity
@Table(name = "sleep_sessions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SleepSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Reference to the user (kept simple for the scaffold). */
    private Long userId;

    private Instant startTime;
    private Instant endTime;

    /** 1..10 quality score */
    private Integer sleepQuality;

    @Column(length = 2000)
    private String notes;

    private Instant createdAt;
    private Instant updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
        updatedAt = createdAt;
    }


    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }
}
