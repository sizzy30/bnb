package com.airbnb.controller;

import com.airbnb.payload.AppUserDto;
import com.airbnb.payload.JWTToken;
import com.airbnb.payload.LoginDto;
import com.airbnb.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private AuthService authService ;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<AppUserDto> createUser(@RequestBody AppUserDto appUserDto){
        appUserDto.setRole("ROLE_USER");
        AppUserDto result=authService.create(appUserDto);
return new ResponseEntity<>(result, HttpStatus.CREATED);
}
    @PostMapping("/createPropertyOwner")
    public ResponseEntity<AppUserDto> createpropertyOwner(@RequestBody AppUserDto appUserDto){
        appUserDto.setRole("ROLE_OWNER");
        AppUserDto result=authService.create(appUserDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    @PostMapping("/createPropertyManager")
    public ResponseEntity<AppUserDto> createpropertyManager(@RequestBody AppUserDto appUserDto){
        appUserDto.setRole("ROLE_MANAGER");
        AppUserDto result=authService.create(appUserDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginDto dto ){
        String result=authService.verifyLogin(dto);
        JWTToken jwtToken=new JWTToken();
        if(result!=null){
            jwtToken.setTokenType("JWT");
            jwtToken.setToken(result);
            return new ResponseEntity<>(jwtToken,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Invalid Username/Password",HttpStatus.UNAUTHORIZED);
        }
}
}
