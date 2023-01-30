package com.niit.FoodieApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Restaurant Not Exist in Database")
public class RestaurantNotExistsException extends Exception{
}
