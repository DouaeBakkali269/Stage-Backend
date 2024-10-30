package net.java.Training_management.controllers;

import net.java.Training_management.entities.Notification;
import net.java.Training_management.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin("*")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    @PostMapping("/send")
    public Notification sendNotification(@RequestBody Notification notification) {
        return notificationRepository.save(notification);
    }

    @GetMapping("/admin")
    public List<Notification> getAdminNotifications() {
        return notificationRepository.findByReceiver("Admin");
    }
    @GetMapping("user/{username}")
    public List<Notification> getUserNotifications(@PathVariable String username){
        return notificationRepository.findBySender(username);
    }


    @PutMapping("/markAsRead")
    public ResponseEntity<?> markAsRead(@RequestBody List<Long> notificationIds) {
        List<Notification> notifications = notificationRepository.findAllById(notificationIds);
        for (Notification notification : notifications) {
            notification.setRead(true);
            notificationRepository.save(notification);  // Ensure this persists the changes
        }
        return ResponseEntity.ok("Notifications marked as read");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletebyId(@PathVariable Long id){
        notificationRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
