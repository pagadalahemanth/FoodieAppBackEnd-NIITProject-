package com.niit.FoodieApp.proxy;

import com.niit.FoodieApp.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "UserAuthenticationApp",url = "localhost:8082/api/v2")
public interface Proxy {
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user);
}
