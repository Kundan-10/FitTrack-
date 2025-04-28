package com.fittrack.FitTrack.serviceimpl;

import com.fittrack.FitTrack.models.Notification;
import com.fittrack.FitTrack.models.User;
import com.fittrack.FitTrack.repository.NotificationRepository;
import com.fittrack.FitTrack.repository.UserRepo;
import com.fittrack.FitTrack.repository.WorkoutRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReminderService {
    private final UserRepo userRepository;
    private final WorkoutRepo workoutRepository;
    private final NotificationRepository notificationRepository;

    /**
     * CRON job that runs daily at 12 AM to remind inactive users
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void sendWorkoutReminderToInactiveUsers() {
        log.info("CRON Job started: Checking inactive users...");

        List<User> users = userRepository.findAll();

        for (User user : users) {
            boolean isActiveRecently = workoutRepository.existsByUserIdAndTimestampAfter(
                    user.getId(),
                    LocalDateTime.now().minusDays(3)
            );

            if (!isActiveRecently) {
                Notification notification = Notification.builder()
                        .user(user)
                        .message("🏃‍♂️ Stay active! You haven't logged any workout recently. Keep moving!")
                        .isRead(false)
                        .build();

                notificationRepository.save(notification);
                log.info("Reminder sent to user: {}", user.getEmail());
            }
        }

        log.info("CRON Job completed: Workout reminders sent.");
    }
}
