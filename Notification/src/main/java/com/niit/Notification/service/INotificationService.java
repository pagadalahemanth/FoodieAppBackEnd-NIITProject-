package com.niit.Notification.service;

import com.niit.Notification.config.RestaurantDTO;
import com.niit.Notification.domain.Notification;

public interface INotificationService {
    public void saveNotifications(RestaurantDTO restaurantDTO);
    public Notification getAllNotification(String email);
}
