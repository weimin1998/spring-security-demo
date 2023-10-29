package com.example.springsecuritydemo.config;

import com.example.springsecuritydemo.handler.MyAuthenticationFailHandler;
import com.example.springsecuritydemo.handler.MyAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 自定义登录页面
        http.formLogin()
                .loginPage("/login.html") // 页面
                .loginProcessingUrl("/login")// 接口，必须和表单提交的接口一样
                //.successForwardUrl("/toMain") // 转发
                .successHandler(new MyAuthenticationSuccessHandler("http://www.baidu.com")) // 重定向
                //.failureForwardUrl("/toFail")
                .failureHandler(new MyAuthenticationFailHandler("http://www.baidu.com"))
                .usernameParameter("username")
                .passwordParameter("password")

        ;



        // 授权，所有请求必须认证才能访问
        http.authorizeRequests()
                .antMatchers("/login.html").permitAll()
                .antMatchers("/fail.html").permitAll()
                .anyRequest().authenticated();

        // 关闭csrf
        http.csrf().disable();
        return http.build();
    }

     // extends WebSecurityConfigurerAdapter
    // WebSecurityConfigurerAdapter 已废弃
    // SecurityFilterChain 作为替代
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
//    }
}
