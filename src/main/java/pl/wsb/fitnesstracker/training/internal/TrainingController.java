package pl.wsb.fitnesstracker.training.internal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.training.api.TrainingDto;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Kontroler REST do obsługi zasobów związanych z treningami.
 * Udostępnia endpointy do tworzenia, aktualizacji i pobierania treningów.
 */

@RestController
@RequestMapping("/v1/trainings")
public class TrainingController {

    private final TrainingServiceImpl trainingService;

    public TrainingController(TrainingServiceImpl trainingService) {
        this.trainingService = trainingService;
    }

    /**
     * Zwraca listę wszystkich treningów.
     */
    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings();
    }

    /**
     * Zwraca trening o podanym ID.
     *
     * @param id identyfikator treningu
     * @return trening lub 404 jeśli nie znaleziono
     */
    @GetMapping("/{id}")
    public ResponseEntity<TrainingDto> getTraining(@PathVariable Long id) {
        Optional<TrainingDto> training = trainingService.getTraining(id);
        return training.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Zwraca treningi użytkownika o podanym ID.
     *
     * @param userId identyfikator użytkownika
     */
    @GetMapping("/user/{userId}")
    public List<TrainingDto> getTrainingsByUser(@PathVariable Long userId) {
        return trainingService.getByUser(userId);
    }

    /**
     * Zwraca treningi według typu aktywności.
     *
     * @param activityType typ aktywności
     */
    @GetMapping("/activity/{activityType}")
    public List<TrainingDto> getTrainingsByActivity(@PathVariable ActivityType activityType) {
        return trainingService.getByActivity(activityType);
    }

    /**
     * Zwraca treningi zakończone po podanej dacie.
     *
     * @param date data w formacie ISO
     */
    @GetMapping("/ended-after")
    public List<TrainingDto> getTrainingsEndedAfter(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date) {
        return trainingService.getAfterDate(date);
    }

    /**
     * Tworzy nowy trening.
     *
     * @param dto dane treningu
     * @return utworzony trening
     */
    @PostMapping
    public ResponseEntity<TrainingDto> createTraining(@RequestBody TrainingDto dto) {
        TrainingDto created = trainingService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Aktualizuje istniejący trening.
     *
     * @param id  identyfikator treningu
     * @param dto dane do aktualizacji
     * @return zaktualizowany trening
     */
    @PutMapping("/{id}")
    public ResponseEntity<TrainingDto> updateTraining(@PathVariable Long id, @RequestBody TrainingDto dto) {
        TrainingDto updated = trainingService.update(id, dto);
        return ResponseEntity.ok(updated);
    }
}
