package net.java.Training_management.repositories;

import net.java.Training_management.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findByReceiver(String receiver);
    List<Notification> findBySender(String sender);

        @Modifying
        @Query("UPDATE Notification n SET n.isRead = true WHERE n.receiver = :receiver")
        void markAllAsRead(@Param("receiver") String receiver);

        @Modifying
        @Query("UPDATE Notification n SET n.isRead = true WHERE n.id IN :notificationIds")
        void markSpecificNotificationsAsRead(@Param("notificationIds") List<Long> notificationIds);

}
