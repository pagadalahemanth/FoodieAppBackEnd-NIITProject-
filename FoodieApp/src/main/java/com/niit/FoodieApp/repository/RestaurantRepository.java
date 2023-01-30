package com.niit.FoodieApp.repository;

import com.niit.FoodieApp.domain.Food;
import com.niit.FoodieApp.domain.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant,String> {
//    @Query("{'foodList.itemName':{$in:[?0]}}")
//        @Query(value = "{ 'restaurantName': ?0 }, { $elemMatch: { 'foodList.itemName' : ?1 } }", delete = true)
//    List<Restaurant> deleteByItemName(String restaurantName,String itemName);

//    @Query(value = "{ 'restaurantName': ?0, 'foodList.itemName': ?1}", delete = true)
//    public void deleteByResNameAndItem(String restaurantName, String itemName);
//    @Query(value = "{'foodList.itemName' : ?0}", delete = true)
//    public boolean deleteByItemName(String itemName);
}
