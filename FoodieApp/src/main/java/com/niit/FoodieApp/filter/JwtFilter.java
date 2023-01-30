package com.niit.FoodieApp.filter;

import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;//typecasting to Http req/res
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;//''        ''      ''
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        String authHeader =httpServletRequest.getHeader("Authorization");

        System.out.println("****************authHeader  "+ authHeader+"***************");
        if(authHeader==null || !authHeader.startsWith("Bearer")){
//token is missing
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            servletOutputStream.println("Missing or Invalid token");
            servletOutputStream.close();
        }else {
//token is valid
            String jwtToken =authHeader.substring(7);
            String username = Jwts.parser().setSigningKey("securityKey").parseClaimsJws(jwtToken).getBody().getSubject();
            System.out.println("*******userName  "+ username+"***************");
            httpServletRequest.setAttribute("firstname",username);
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}
