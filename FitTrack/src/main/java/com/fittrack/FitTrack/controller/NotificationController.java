package com.fittrack.FitTrack.controller;

import com.fittrack.FitTrack.exception.NotificationException;
import com.fittrack.FitTrack.exception.UserException;
import com.fittrack.FitTrack.models.Notification;
import com.fittrack.FitTrack.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
@Tag(name = "Notification APIs", description = "APIs to manage user notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Operation(summary = "Send notification to user", description = "Send a notification message to the authenticated user")
    @PostMapping
    public ResponseEntity<Notification> sendNotification(@RequestParam String message) throws UserException {
        Notification notification = notificationService.sendNotification(message);
        return new ResponseEntity<>(notification, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all notifications", description = "Retrieve all notifications for the authenticated user")
    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotificationsForUser() throws UserException {
        List<Notification> notifications = notificationService.getAllNotificationsForUser();
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @Operation(summary = "Mark notification as read", description = "Mark a specific notification as read for the authenticated user")
    @PutMapping("/{notificationId}")
    public ResponseEntity<Notification> markNotificationAsRead(@PathVariable Integer notificationId) throws UserException, NotificationException, NotificationException {
        Notification updatedNotification = notificationService.markNotificationAsRead(notificationId);
        return new ResponseEntity<>(updatedNotification, HttpStatus.OK);
    }
}