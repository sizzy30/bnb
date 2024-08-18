package com.airbnb;


import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class A {
    public static void main(String[] args){
        String hashpw = BCrypt.hashpw("masaw@123", BCrypt.gensalt(10));
        System.out.println(hashpw);
}}
