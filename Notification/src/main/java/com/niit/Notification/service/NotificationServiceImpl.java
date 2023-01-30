package com.niit.Notification.service;

import com.niit.Notification.config.RestaurantDTO;
import com.niit.Notification.domain.Notification;
import com.niit.Notification.repository.NotificationRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements INotificationService{

    @Autowired
    private NotificationRepository repository;

    @RabbitListener(queues = "food-queue")
    @Override
    public void saveNotifications(RestaurantDTO restaurantDTO) {
        Notification notification = new Notification();
        String email = restaurantDTO.getJsonObject().get("email").toString();
        if(repository.findById(email).isEmpty()){
            notification.setEmail(email);
        }
        notification.setMessage("Registered Successfully");
//        notification.setEmail(restaurantDTO.getJsonObject().toJSONString());
        notification.setEmail(restaurantDTO.getJsonObject().toJSONString());
        repository.save(notification);
    }

    @Override
    public Notification getAllNotification(String email) {
        return repository.findById(email).get();
    }
}
