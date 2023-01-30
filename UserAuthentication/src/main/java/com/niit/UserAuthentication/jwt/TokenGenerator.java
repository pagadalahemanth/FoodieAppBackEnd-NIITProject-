package com.niit.UserAuthentication.jwt;

import com.niit.UserAuthentication.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenGenerator implements ITokenGenerator{

    @Override
    public Map<String, String> tokenGenerator(User user) {
        //this method will return key value pairs like token: jwt token, using map.
        // Both key and values are string
        String jwtToken = null;  // initialising String
        jwtToken = Jwts.builder() // jwtbuilder instance
                .setSubject(user.getUsertype()) //setting subject for the token  (subject : whom the token refer to)
                .setIssuedAt(new Date()) //generates the Token Issue date and time
                .signWith(SignatureAlgorithm.HS256, "security")
                //It will specicy which used to encoding the algorithm for token
                // specified signature algorithm and using provided key.
                .compact();
        System.out.println(jwtToken);
        Map<String, String> map = new HashMap<>(); //initialising Hashmap to store data
        map.put("token",jwtToken);
        map.put("message", "User Logged in Successfully");
        System.out.println(user.getEmail());
        System.out.println(user.getUsertype());
        map.put("usertype", user.getUsertype());
        map.put("image", user.getImage());
        map.put("email",user.getEmail());
        return map;
    }
}
