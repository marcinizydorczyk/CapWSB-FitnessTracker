package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.user.api.User;

public class TrainingMapper {
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
