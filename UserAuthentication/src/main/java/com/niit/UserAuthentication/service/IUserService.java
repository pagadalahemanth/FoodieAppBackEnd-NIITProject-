package com.niit.UserAuthentication.service;

import com.niit.UserAuthentication.domain.User;
import com.niit.UserAuthentication.exception.PasswordException;
import com.niit.UserAuthentication.exception.UserAlreadyExistsException;
import com.niit.UserAuthentication.exception.UserNotFoundException;

public interface IUserService {

    public User registerUser(User user) throws UserAlreadyExistsException;
    public User login(String email,String password) throws PasswordException, UserNotFoundException;
}
