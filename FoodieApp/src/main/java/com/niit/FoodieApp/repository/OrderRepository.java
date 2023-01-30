package com.niit.FoodieApp.repository;

import com.niit.FoodieApp.domain.Food;
import com.niit.FoodieApp.domain.Order;
import com.niit.FoodieApp.domain.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, Food> {

    @Query(value = "{'foodList.itemName' : ?0}", delete = true)
    public void deleteByItemName(String itemName);

    public List<Order> findByEmailId(String emailId);
}
