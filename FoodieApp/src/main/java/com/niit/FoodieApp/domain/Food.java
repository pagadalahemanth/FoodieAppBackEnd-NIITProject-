package com.niit.FoodieApp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class Food {
    @Id
    private String itemName;
    private String image;
    private int price;
    private int rating;
    private String itemType;
    private String emailId;
//    private int quantity;
}
