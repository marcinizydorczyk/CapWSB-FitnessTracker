package pl.wsb.fitnesstracker.training.api;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.training.internal.TrainingServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<TrainingDto> getTraining(@PathVariable Long id) {
        Optional<TrainingDto> training = trainingService.getTraining(id);
        return training.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<TrainingDto> getTrainingsByUser(@PathVariable Long userId) {
        return trainingService.getByUser(userId);
    }

    @GetMapping("/activity/{activityType}")
    public List<TrainingDto> getTrainingsByActivity(@PathVariable ActivityType activityType) {
        return trainingService.getByActivity(activityType);
    }

    @GetMapping("/ended-after")
    public List<TrainingDto> getTrainingsEndedAfter(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date) {
        return trainingService.getAfterDate(date);
    }

    @PostMapping
    public ResponseEntity<TrainingDto> createTraining(@RequestBody TrainingDto dto) {
        TrainingDto created = trainingService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingDto> updateTraining(@PathVariable Long id, @RequestBody TrainingDto dto) {
        TrainingDto updated = trainingService.update(id, dto);
        return ResponseEntity.ok(updated);
    }
}
