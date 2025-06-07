package pl.wsb.fitnesstracker.training.api;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.training.internal.TrainingServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
public class TrainingController {
    private final TrainingServiceImpl trainingService;

    public TrainingController(TrainingServiceImpl trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings();
    }

    @GetMapping("/user/{userId}")
    public List<TrainingDto> getTrainingsByUser(@PathVariable Long userId) {
        return trainingService.getTrainingsByUserId(userId);
    }

    @GetMapping("/ended-after")
    public List<TrainingDto> getTrainingsEndedAfter(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return trainingService.getTrainingsEndedAfter(date);
    }

    @GetMapping("/activity/{activityType}")
    public List<TrainingDto> getTrainingsByActivity(@PathVariable String activityType) {
        return trainingService.getTrainingsByActivity(activityType);
    }

    @PostMapping
    public ResponseEntity<TrainingDto> createTraining(@RequestBody TrainingDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(trainingService.createTraining(dto));
    }

    @PatchMapping("/{id}/distance")
    public TrainingDto updateDistance(@PathVariable Long id, @RequestParam double distance) {
        return trainingService.updateDistance(id, distance);
    }
}
