package com.niit.UserAuthentication.jwt;

import com.niit.UserAuthentication.domain.User;

import java.util.Map;

public interface ITokenGenerator {
    public Map<String,String> tokenGenerator(User user);
}
