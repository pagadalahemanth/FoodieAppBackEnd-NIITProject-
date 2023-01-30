package com.niit.UserAuthentication.controller;

import com.niit.UserAuthentication.domain.User;
import com.niit.UserAuthentication.exception.PasswordException;
import com.niit.UserAuthentication.exception.UserAlreadyExistsException;
import com.niit.UserAuthentication.exception.UserNotFoundException;
import com.niit.UserAuthentication.jwt.ITokenGenerator;
import com.niit.UserAuthentication.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v2")
//@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private IUserService userService;
    private ITokenGenerator tokenGenerator;
    @Autowired
    public UserController(IUserService userService, ITokenGenerator tokenGenerator) {
        this.userService = userService;
        this.tokenGenerator = tokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistsException {
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody User user) throws PasswordException, UserNotFoundException {
        Map<String, String> error = new HashMap<>();
        error.put("message","invalid");
        User data = userService.login(user.getEmail(),user.getPassword()); //getting gmail and password from client and calling service method
        System.out.println("data"+data);
        if(data!=null){
            Map<String, String> map =  tokenGenerator.tokenGenerator(data);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
}
