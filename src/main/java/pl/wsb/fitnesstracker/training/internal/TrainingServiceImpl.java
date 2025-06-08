package pl.wsb.fitnesstracker.training.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.*;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;

import java.util.List;
import java.util.Optional;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Implementacja serwisu do zarządzania treningami.
 * Odpowiada za operacje CRUD na obiektach treningów oraz ich pobieranie według różnych kryteriów.
 */
@Service
public class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;
    private final UserProvider userProvider;

    @Autowired
    public TrainingServiceImpl(TrainingRepository trainingRepository, UserProvider userProvider) {
        this.trainingRepository = trainingRepository;
        this.userProvider = userProvider;
    }

    /**
     * Pobiera listę wszystkich treningów w formie DTO.
     *
     * @return lista treningów
     */
    public List<TrainingDto> getAllTrainings() {
        return trainingRepository.findAll().stream()
                .map(training -> TrainingMapper.toDto(training, training.getUser()))
                .collect(Collectors.toList());
    }
    /**
     * Pobiera trening po jego identyfikatorze.
     *
     * @param id identyfikator treningu
     * @return Optional zawierający DTO treningu jeśli istnieje
     */
    public Optional<TrainingDto> getTraining(Long id) {
        return trainingRepository.findById(id)
                .map(training -> TrainingMapper.toDto(training, training.getUser()));
    }

    /**
     * Pobiera listę treningów przypisanych do użytkownika o danym identyfikatorze.
     *
     * @param userId identyfikator użytkownika
     * @return lista treningów użytkownika
     */
    public List<TrainingDto> getByUser(Long userId) {
        return trainingRepository.findByUserId(userId).stream()
                .filter(training -> training.getUser().getId().equals(userId))
                .map(training -> TrainingMapper.toDto(training, training.getUser()))
                .collect(Collectors.toList());
    }

    /**
     * Pobiera listę treningów o określonym typie aktywności.
     *
     * @param activityType typ aktywności
     * @return lista treningów
     */
    public List<TrainingDto> getByActivity(ActivityType activityType) {
        return trainingRepository.findAll().stream()
                .filter(training -> training.getActivityType() == activityType)
                .map(training -> TrainingMapper.toDto(training, training.getUser()))
                .collect(Collectors.toList());
    }


    /**
     * Pobiera listę treningów zakończonych po podanej dacie.
     *
     * @param date data graniczna
     * @return lista treningów
     */
    public List<TrainingDto> getAfterDate(Date date) {
        return trainingRepository.findAll().stream()
                .filter(training -> training.getEndTime() != null && training.getEndTime().after(date))
                .map(training -> TrainingMapper.toDto(training, training.getUser()))
                .collect(Collectors.toList());
    }

    /**
     * Tworzy nowy trening na podstawie przekazanego DTO.
     *
     * @param dto dane treningu
     * @return utworzony trening w formie DTO
     */
    public TrainingDto create(TrainingDto dto) {
        User user = userProvider.getUser(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUserId()));
        Training training = TrainingMapper.fromDto(dto, user);
        Training saved = trainingRepository.save(training);
        return TrainingMapper.toDto(saved, user);
    }

    /**
     * Aktualizuje istniejący trening o podanym identyfikatorze na podstawie danych z DTO.
     *
     * @param id  identyfikator treningu
     * @param dto nowe dane treningu
     * @return zaktualizowany trening w formie DTO
     */
    public TrainingDto update(Long id, TrainingDto dto) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training not found with id: " + id));

        training.setDistance(dto.getDistance());
        training.setActivityType(dto.getActivityType());
        training.setStartTime(dto.getStartTime());
        training.setEndTime(dto.getEndTime());
        training.setAverageSpeed(dto.getAverageSpeed());

        Training updated = trainingRepository.save(training);
        return TrainingMapper.toDto(updated, training.getUser());
    }
}
