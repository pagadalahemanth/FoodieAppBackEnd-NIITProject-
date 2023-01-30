package com.niit.FoodieApp.service;

import com.niit.FoodieApp.config.RestaurantDTO;
import com.niit.FoodieApp.domain.Food;
import com.niit.FoodieApp.domain.Order;
import com.niit.FoodieApp.domain.Restaurant;
import com.niit.FoodieApp.domain.User;
import com.niit.FoodieApp.exception.PasswordMisMatchException;
import com.niit.FoodieApp.exception.RestaurantAlreadyExists;
import com.niit.FoodieApp.exception.RestaurantNotExistsException;
import com.niit.FoodieApp.exception.UserAlreadyExistsException;
import com.niit.FoodieApp.proxy.Proxy;
import com.niit.FoodieApp.repository.FoodRepository;
import com.niit.FoodieApp.repository.OrderRepository;
import com.niit.FoodieApp.repository.RestaurantRepository;
import com.niit.FoodieApp.repository.UserRepository;
import org.json.simple.JSONObject;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RestaurantServiceImpl implements IRestaurantService{

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Proxy proxy;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private DirectExchange directExchange;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        if(userRepository.findById(user.getEmail()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        else {
            proxy.registerUser(user);
            RestaurantDTO restaurantDTO = new RestaurantDTO();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("email",user.getEmail());
//            jsonObject.put("Registered successfully","");
            restaurantDTO.setJsonObject(jsonObject);
            rabbitTemplate.convertAndSend(directExchange.getName(),"food-routing",restaurantDTO);
            return userRepository.save(user);
        }
    }

    @Override
    public Restaurant saveRestaurantDetails(Restaurant restaurant) throws RestaurantAlreadyExists {
        if(restaurantRepository.findById(restaurant.getRestaurantName()).isPresent()){
            throw new RestaurantAlreadyExists();
        }else
        return restaurantRepository.save(restaurant);
    }

    @Override
    public List<Restaurant> getRestaurantDetails() {
        return restaurantRepository.findAll();
    }

    @Override
    public boolean checkRestaurantName(String restaurantName,String password) throws RestaurantNotExistsException, PasswordMisMatchException {
        if(restaurantRepository.findById(restaurantName).isPresent()){
           Restaurant restaurant=restaurantRepository.findById(restaurantName).get();
           if(restaurant.getRestaurantName().equals(restaurantName) && restaurant.getPassword().equals(password)){
               return true;
           }
           else{
               throw new PasswordMisMatchException();
           }

        }else
        {
            throw new RestaurantNotExistsException();
        }
    }

    @Override
    public Restaurant addItemsToRestaurant(String restaurantName, Food food) throws RestaurantNotExistsException {
        if(restaurantRepository.findById(restaurantName).isEmpty()){
            throw new RestaurantNotExistsException();
        }
        Restaurant result=restaurantRepository.findById(restaurantName).get();
        if(result.getFoodList()!=null)
        {
            System.out.println("Already exist executed");
            result.getFoodList().add(food);
        }
        else{
            System.out.println("----------------------------");
            result.setFoodList(new ArrayList<>());
            result.getFoodList().add(food);
        }
        restaurantRepository.save(result);
        return result;
    }

//    @Override
//    public List<Restaurant> getAllItemsInRestaurant(String itemName) {
//        return restaurantRepository.findByItemName(itemName);
//    }

    @Override
    public Food getOneItem(String restaurantName,String itemName) throws RestaurantNotExistsException {
        if(restaurantRepository.findById(restaurantName).isEmpty())
        {
            throw new RestaurantNotExistsException();
        }
//         restaurantRepository.findByIdAndItemName(restaurantName,itemName);
        List<Food> foods=restaurantRepository.findById(restaurantName).get().getFoodList();
        Food foodItem=null;
        for(Food food:foods)
        {
            if(food.getItemName().equals(itemName))
            {
                foodItem=food;
            }
        }
        return foodItem;
    }

    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrderDetails(String emailId) {
        return orderRepository.findByEmailId(emailId);
    }

    @Override
    public boolean deleteCart() {
        orderRepository.deleteAll();
        return true;
    }

    @Override
    public boolean deleteItemInCart(String itemName) {
        orderRepository.deleteByItemName(itemName);
        return true;
    }


    @Override
    public boolean addToFav(Food food) {
        List<Food> food1;
        food1 = foodRepository.findAll();
        System.out.println("food1"+food1);
        if (food1.contains(food)) {
            return false;
        } else {
            foodRepository.save(food);
            return true;
        }
    }

    @Override
    public List<Food> getFav(String emailId) {

        return foodRepository.findByEmailId(emailId);
    }

    @Override
    public boolean deleteFavById(String itemName) {
        foodRepository.deleteById(itemName);
        return true;
    }

    @Override
    public boolean deleteRes(String restaurantName) {
        restaurantRepository.deleteById(restaurantName);
        return true;
    }

    @Override
    public Restaurant getByRestaurantNameee(String restaurantName) {
        return restaurantRepository.findById(restaurantName).get();
    }

    @Override
    public boolean deleteByItemInRestaurant(String restaurantName,Food itemName) {
        if (restaurantRepository.findById(restaurantName).isPresent()){
            Restaurant result=restaurantRepository.findById(restaurantName).get();

            System.out.println(itemName);
            result.getFoodList().remove(itemName);
            restaurantRepository.save(result);

            return true;
        }else {
            return false;
        }
    }
    }

