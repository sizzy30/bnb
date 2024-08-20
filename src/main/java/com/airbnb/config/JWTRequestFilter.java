package com.airbnb.config;

import com.airbnb.repository.AppUserRepository;
import com.airbnb.entity.AppUser;
import com.airbnb.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {
private JWTService jwtService;
private AppUserRepository appUserRepository;

    public JWTRequestFilter(JWTService jwtService, AppUserRepository appUserRepository) {
        this.jwtService = jwtService;
        this.appUserRepository = appUserRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if(header!=null&&header.startsWith("Bearer ")) {
            String token = header.substring(8, header.length() - 1);
         String username = jwtService.getUsername(token);
            Optional<AppUser> opUser=appUserRepository.findByUsername(username);
            if(opUser.isPresent()){
                AppUser appUser = opUser.get();
                UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(appUser,
                        null, Collections.singleton(new SimpleGrantedAuthority(appUser.getRole())));
                auth.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request,response);
    }
}
