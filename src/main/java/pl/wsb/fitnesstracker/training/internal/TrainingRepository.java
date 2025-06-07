package pl.wsb.fitnesstracker.training.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.training.api.Training;

import java.time.LocalDateTime;
import java.util.List;

interface TrainingRepository extends JpaRepository<Training, Long> {
    List<Training> findByUserId(Long userId);
    List<Training> findByActivity(String activityType);
    List<Training> findByEndDateAfter(LocalDateTime date);
}
