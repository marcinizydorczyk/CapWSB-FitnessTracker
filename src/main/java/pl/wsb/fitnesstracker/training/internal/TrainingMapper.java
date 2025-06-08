package pl.wsb.fitnesstracker.training.internal;

import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import pl.wsb.fitnesstracker.user.api.User;

/**
 * Klasa pomocnicza do mapowania między encją Training a DTO TrainingDto.
 */
public class TrainingMapper {
    /**
     * Konwertuje obiekt encji Training na obiekt DTO.
     *
     * @param training obiekt encji Training
     * @param user     użytkownik powiązany z treningiem
     * @return TrainingDto reprezentujący dany trening
     */
    public static TrainingDto toDto(Training training, User user) {
        TrainingDto dto = new TrainingDto();
        dto.setId(training.getId());
        dto.setUserId(training.getUser().getId());
        dto.setStartTime(training.getStartTime());
        dto.setEndTime(training.getEndTime());
        dto.setActivityType(training.getActivityType());
        dto.setDistance(training.getDistance());
        dto.setAverageSpeed(training.getAverageSpeed());
        return dto;
    }

    /**
     * Tworzy nowy obiekt encji Training na podstawie danych z TrainingDto.
     *
     * @param dto  obiekt DTO zawierający dane treningu
     * @param user użytkownik powiązany z treningiem
     * @return nowy obiekt Training
     */
    public static Training fromDto(TrainingDto dto, User user) {
        return new Training(
                user,
                dto.getStartTime(),
                dto.getEndTime(),
                dto.getActivityType(),
                dto.getDistance(),
                dto.getAverageSpeed()
        );
    }
}
