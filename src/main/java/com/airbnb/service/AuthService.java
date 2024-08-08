package com.airbnb.service;

import com.airbnb.payload.AppUserDto;
import com.airbnb.payload.LoginDto;

public interface AuthService {
    AppUserDto create(AppUserDto appUserDto);

    String verifyLogin(LoginDto dto);
}
