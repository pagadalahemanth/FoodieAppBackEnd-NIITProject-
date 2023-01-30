package com.niit.FoodieApp.service;

import com.niit.FoodieApp.domain.Food;
import com.niit.FoodieApp.domain.Order;
import com.niit.FoodieApp.domain.Restaurant;
import com.niit.FoodieApp.domain.User;
import com.niit.FoodieApp.exception.PasswordMisMatchException;
import com.niit.FoodieApp.exception.RestaurantAlreadyExists;
import com.niit.FoodieApp.exception.RestaurantNotExistsException;
import com.niit.FoodieApp.exception.UserAlreadyExistsException;

import java.util.List;

public interface IRestaurantService {
    public User registerUser(User user) throws UserAlreadyExistsException;
    public Restaurant saveRestaurantDetails(Restaurant restaurant) throws RestaurantAlreadyExists;
    public List<Restaurant> getRestaurantDetails();
    public boolean checkRestaurantName(String restaurantName,String password) throws RestaurantNotExistsException, PasswordMisMatchException;
    public Restaurant addItemsToRestaurant(String restaurantName,Food food) throws RestaurantNotExistsException;
//    public List<Restaurant> getAllItemsInRestaurant(String itemName);//search
    public Food getOneItem(String restaurantName,String itemName) throws RestaurantNotExistsException;
    public Order addOrder(Order order);
    public List<Order> getOrderDetails(String emailId);
    public boolean deleteCart();
    public boolean deleteItemInCart(String itemName);
    public boolean addToFav(Food food);
    public List<Food> getFav(String emailId);
    public boolean deleteFavById(String itemName);
    public boolean deleteRes(String restaurantName);
    public Restaurant getByRestaurantNameee(String restaurantName);
    public boolean deleteByItemInRestaurant(String restaurantName,Food itemName);
}
