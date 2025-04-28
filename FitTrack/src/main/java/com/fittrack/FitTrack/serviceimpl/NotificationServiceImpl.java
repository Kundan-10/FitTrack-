package com.fittrack.FitTrack.serviceimpl;

import com.fittrack.FitTrack.exception.NotificationException;
import com.fittrack.FitTrack.exception.UserException;
import com.fittrack.FitTrack.models.Notification;
import com.fittrack.FitTrack.models.User;
import com.fittrack.FitTrack.repository.NotificationRepository;
import com.fittrack.FitTrack.service.NotificationService;
import com.fittrack.FitTrack.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepo;
    private final JwtUtils jwtUtils;

    @Override
    public Notification sendNotification(String message) throws UserException {
        User user = jwtUtils.getAuthenticatedUser();

        Notification notification = Notification.builder()
                .message(message)
                .isRead(false)
                .timestamp(LocalDateTime.now())
                .user(user)
                .build();

        return notificationRepo.save(notification);
    }

    @Override
    public List<Notification> getAllNotificationsForUser() throws UserException {
        User user = jwtUtils.getAuthenticatedUser();
        return notificationRepo.findByUser(user);
    }

    @Override
    public Notification markNotificationAsRead(Integer notificationId) throws UserException, NotificationException {
        Notification notification = notificationRepo.findById(notificationId)
                .orElseThrow(() -> new NotificationException("Notification not found with ID: " + notificationId));

        User user = jwtUtils.getAuthenticatedUser();

        if (!notification.getUser().getId().equals(user.getId())) {
            throw new NotificationException("You are not authorized to update this notification.");
        }

        notification.setRead(true);
        return notificationRepo.save(notification);
    }
}
