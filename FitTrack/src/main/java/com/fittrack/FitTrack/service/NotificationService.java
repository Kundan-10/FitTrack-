package com.fittrack.FitTrack.service;

import com.fittrack.FitTrack.exception.NotificationException;
import com.fittrack.FitTrack.exception.UserException;
import com.fittrack.FitTrack.models.Notification;

import java.util.List;

public interface NotificationService {

    Notification sendNotification(String message) throws UserException;

    List<Notification> getAllNotificationsForUser() throws UserException;

    Notification markNotificationAsRead(Integer notificationId) throws NotificationException, UserException;
}
