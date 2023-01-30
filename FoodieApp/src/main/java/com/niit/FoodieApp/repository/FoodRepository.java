package com.niit.FoodieApp.repository;

import com.niit.FoodieApp.domain.Food;
import com.niit.FoodieApp.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FoodRepository extends MongoRepository<Food,String> {
    public List<Food> findByEmailId(String emailId);
}
