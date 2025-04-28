package com.fittrack.FitTrack.repository;

import com.fittrack.FitTrack.models.Notification;
import com.fittrack.FitTrack.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByUser(User user);
}
