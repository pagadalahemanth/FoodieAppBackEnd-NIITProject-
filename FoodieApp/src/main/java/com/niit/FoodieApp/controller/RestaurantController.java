package com.niit.FoodieApp.controller;

import com.niit.FoodieApp.domain.Food;
import com.niit.FoodieApp.domain.Order;
import com.niit.FoodieApp.domain.Restaurant;
import com.niit.FoodieApp.domain.User;
import com.niit.FoodieApp.exception.PasswordMisMatchException;
import com.niit.FoodieApp.exception.RestaurantAlreadyExists;
import com.niit.FoodieApp.exception.RestaurantNotExistsException;
import com.niit.FoodieApp.exception.UserAlreadyExistsException;
import com.niit.FoodieApp.service.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
//@CrossOrigin(origins = "http://localhost:4200")
public class RestaurantController {
    private IRestaurantService restaurantService;

    @Autowired
    public RestaurantController(IRestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }
    @PostMapping("/registers")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistsException {
        return new ResponseEntity<>(restaurantService.registerUser(user),HttpStatus.OK);
    }

    @PostMapping("/restaurant")
    public ResponseEntity<?> saveRestaurant(@RequestBody Restaurant restaurant) throws RestaurantAlreadyExists {
        return new ResponseEntity<>(restaurantService.saveRestaurantDetails(restaurant), HttpStatus.CREATED);
    }
    @GetMapping("/restaurant")
    public ResponseEntity<?> getRestaurantDetails(){
        return new ResponseEntity<>(restaurantService.getRestaurantDetails(),HttpStatus.OK);
    }
    @GetMapping("/check/{restaurantName}/{password}")
    public ResponseEntity<?> checkRestaurant(@PathVariable String restaurantName,@PathVariable String password) throws RestaurantNotExistsException, PasswordMisMatchException {
        return new ResponseEntity<>(restaurantService.checkRestaurantName(restaurantName,password),HttpStatus.OK);
    }

    @PostMapping("/addItems/{restaurantName}")
    public ResponseEntity<?> addItems(@RequestBody Food food,@PathVariable String restaurantName) throws RestaurantNotExistsException {
        return new ResponseEntity<>(restaurantService.addItemsToRestaurant(restaurantName,food),HttpStatus.CREATED);
    }
//    @GetMapping("restaurant/{itemName}")
//    public ResponseEntity<?> getAllItemsInRestaurant(@PathVariable String itemName){
//        return new ResponseEntity<>(restaurantService.getAllItemsInRestaurant(itemName),HttpStatus.OK);
//    }
    @GetMapping("restaurant/{restaurantName}/{itemName}")
    public ResponseEntity<?> getOneItem(@PathVariable String restaurantName, @PathVariable String itemName) throws RestaurantNotExistsException {

        return new ResponseEntity<>(restaurantService.getOneItem(restaurantName,itemName),HttpStatus.OK);
    }
    @PostMapping("/order")
    public ResponseEntity<?> saveOrders(HttpServletRequest request,@RequestBody Order order){
        System.out.println(request.getAttribute("firstname"));
        return new ResponseEntity<>(restaurantService.addOrder(order),HttpStatus.OK);
    }
    @GetMapping("/orders/{emailId}")
    public ResponseEntity<?> getOrders(@PathVariable String emailId){
        return new ResponseEntity<>(restaurantService.getOrderDetails(emailId),HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteOrders(){
        return new ResponseEntity<>(restaurantService.deleteCart(),HttpStatus.OK);
    }
    @DeleteMapping("/deleteById/{itemName}")
    public ResponseEntity<?> deleteItemInCart(@PathVariable String itemName){
        return new ResponseEntity<>(restaurantService.deleteItemInCart(itemName),HttpStatus.OK);
    }
    @PostMapping("/addToFav")
    public ResponseEntity<?> addFav(HttpServletRequest request, @RequestBody Food food){
        System.out.println(request.getAttribute("firstname"));
        return new ResponseEntity<>(restaurantService.addToFav(food),HttpStatus.OK);
    }
    @GetMapping("/favorites/{emailId}")
    public ResponseEntity<?> getFav(@PathVariable String emailId){
        return new ResponseEntity<>(restaurantService.getFav(emailId),HttpStatus.OK);
    }
    @DeleteMapping("/deleteFav/{itemName}")
    public ResponseEntity<?> deleteFav(@PathVariable String itemName){
        return new ResponseEntity<>(restaurantService.deleteFavById(itemName),HttpStatus.OK);
    }
    @DeleteMapping("/adminDelRes/{restaurantName}")
    public ResponseEntity<?> deleteRes(@PathVariable String restaurantName){
        return new ResponseEntity<>(restaurantService.deleteRes(restaurantName),HttpStatus.OK);
    }
    @GetMapping("/restaurantOwner/{restaurantName}")
    public ResponseEntity<?> getRestDetails(@PathVariable String restaurantName){
        return new ResponseEntity<>(restaurantService.getByRestaurantNameee(restaurantName),HttpStatus.OK);
    }
    @PostMapping("/deleteItem/{restaurantName}")
    public ResponseEntity<?> deleteItemInResOwner(@PathVariable String restaurantName,@RequestBody Food itemName){
        return new ResponseEntity<>(restaurantService.deleteByItemInRestaurant(restaurantName,itemName),HttpStatus.OK);
    }

}
