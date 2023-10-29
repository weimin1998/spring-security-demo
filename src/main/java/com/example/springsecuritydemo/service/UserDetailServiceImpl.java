package com.example.springsecuritydemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;
    private static Map<String,String> mockPWD = new HashMap<>();
    private static Map<String,String> mockAuthority = new HashMap<>();

    static {
        mockPWD.put("admin","$2a$10$Fsc3gnrCUTDKAMAzaWl/qObReI.1lzN/u4Yu9.tnKI2aB/yfr7Oau");// admin
        mockPWD.put("weimin","$2a$10$oq.Fi3E2HokXIPXpU3QUye8T/iZJCFvg8tDHZAA5OGv798Q5t8kAi");// 123
        mockPWD.put("root","$2a$10$VynGwj3KTTJryWS5ZLRQ0e/h2uqiwfLMzST7cRQT1Ci91AeGP0beK");// root


        mockAuthority.put("admin","admin,normal");
        mockAuthority.put("weimin","normal");
        mockAuthority.put("root","root,normal");
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if(!mockPWD.containsKey(username)){
            throw new UsernameNotFoundException("用户名不存在！");
        }
        String pwdInDB = mockPWD.get(username);
        return new User(username, pwdInDB, AuthorityUtils.commaSeparatedStringToAuthorityList(mockAuthority.get(username)));
    }
}
