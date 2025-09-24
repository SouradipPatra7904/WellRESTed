package souradippatra.WellRESTed.repository;

import souradippatra.WellRESTed.domain.SleepSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;


public interface SleepSessionRepository extends JpaRepository < SleepSession, Long > {


    Page < SleepSession > findByUserId(Long userId, Pageable pageable);


    // Keyset/seek sample: return sessions after a given id (ordered by id asc), pageable applies limit
    @Query("SELECT s FROM SleepSession s WHERE s.userId = :userId AND (:lastId IS NULL OR s.id > :lastId) ORDER BY s.id ASC")
    List < SleepSession > seekByUserIdAfter(@Param("userId") Long userId, @Param("lastId") Long lastId, Pageable pageable);
}