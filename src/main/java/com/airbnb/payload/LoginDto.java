package com.airbnb.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto
{

    private static String username;

    private String password;

    public static String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        LoginDto.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
