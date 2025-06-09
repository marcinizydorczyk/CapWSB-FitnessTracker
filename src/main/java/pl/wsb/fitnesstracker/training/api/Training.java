package pl.wsb.fitnesstracker.training.api;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Reprezentuje pojedynczy trening wykonany przez użytkownika.
 * Zawiera informacje takie jak czas trwania, typ aktywności, dystans i prędkość.
 */
@Entity
@Table(name = "trainings")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Training {

    /**
     * Unikalny identyfikator treningu.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Użytkownik, do którego należy trening.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Data i godzina rozpoczęcia treningu.
     */
    @Column(name = "start_time", nullable = false)
    private Date startTime;

    /**
     * Data i godzina zakończenia treningu.
     */
    @Column(name = "end_time", nullable = false)
    private Date endTime;

    /**
     * Typ aktywności (np. bieganie, jazda na rowerze).
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "activity_type", nullable = false)
    private ActivityType activityType;

    /**
     * Przebyty dystans w kilometrach.
     */
    @Column(name = "distance")
    private double distance;

    /**
     * Średnia prędkość w trakcie treningu.
     */
    @Column(name = "average_speed")
    private double averageSpeed;

    public Training(
            final User user,
            final Date startTime,
            final Date endTime,
            final ActivityType activityType,
            final double distance,
            final double averageSpeed) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }
    // === Gettery i settery ===

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public void setAverageSpeed(Double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }
}