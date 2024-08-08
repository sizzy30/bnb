package com.airbnb.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authantication");
        System.out.println(token);//"Bearer "jhdgcfdkgf--------
        if(token!=null && token.startsWith("Bearer ")){
            String substring = token.substring(8, token.length() - 1);
        }

    }
}
