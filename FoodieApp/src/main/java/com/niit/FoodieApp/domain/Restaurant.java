package com.niit.FoodieApp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Restaurant {
    @Id
    private String restaurantName;
    private String password;
    private String location;
    private List<Food> foodList;
}
