package com.itheima.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderUtils {

     public static String  getPasswordEncoder(String password){

         PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
         return passwordEncoder.encode(password);
    }
}
