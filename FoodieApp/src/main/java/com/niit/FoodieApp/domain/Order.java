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
public class Order {

    @Id
    private Food foodList;
    private int quantity;
    private int totalPrice;
    private String emailId;
//    private String deliveryAddress;

}
