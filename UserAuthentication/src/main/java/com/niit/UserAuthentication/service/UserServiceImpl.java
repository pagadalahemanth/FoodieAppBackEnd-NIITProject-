package com.niit.UserAuthentication.service;

import com.niit.UserAuthentication.domain.User;
import com.niit.UserAuthentication.exception.PasswordException;
import com.niit.UserAuthentication.exception.UserAlreadyExistsException;
import com.niit.UserAuthentication.exception.UserNotFoundException;
import com.niit.UserAuthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        if(userRepository.findById(user.getEmail()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        return userRepository.save(user);
    }

    @Override
    public User login(String email, String password) throws PasswordException, UserNotFoundException {
        if (userRepository.findById(email).isPresent()){
            User user = userRepository.findById(email).get();
            if (user.getPassword().equals(password)){
                user.setPassword("PPP");
                return user;
            } else {
                throw new PasswordException();
            }
        }else {
            throw new UserNotFoundException();
        }
    }
}
