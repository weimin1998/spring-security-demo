package com.example.springsecuritydemo;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Base64;

public class TestClass {

    @Test
    public void test(){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String string1 = passwordEncoder.encode("admin");
        String string2 = passwordEncoder.encode("123");
        String string3 = passwordEncoder.encode("root");


        System.out.println(string1);
        System.out.println(string2);
        System.out.println(string3);
    }
}
