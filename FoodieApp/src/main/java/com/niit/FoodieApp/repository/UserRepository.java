package com.niit.FoodieApp.repository;

import com.niit.FoodieApp.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {

}
