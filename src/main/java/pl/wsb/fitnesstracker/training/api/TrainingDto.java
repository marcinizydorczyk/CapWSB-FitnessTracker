package pl.wsb.fitnesstracker.training.api;

import lombok.Data;
import pl.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.Date;

@Data
public class TrainingDto {
    private Long id;
    private Date startTime;
    private Date endTime;
    private ActivityType activityType;
    private double distance;
    private Date startDate;
    private Date endDate;
    private Long userId;
    private double averageSpeed;

}
