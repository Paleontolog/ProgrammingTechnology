package com.bankapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

//        // System stuff
//        http.authorizeRequests()//
//                .antMatchers("/favicon.ico").permitAll();
//
////        // Pages
////        http.authorizeRequests()//
////                .antMatchers("/v1/**").permitAll()//
////                .antMatchers("/v2/**").permitAll()//
////                .antMatchers("/v3/**").permitAll()//
////                .antMatchers("/v4/**").permitAll()//
////                .antMatchers("/v5/**").permitAll()//
////                // disallow everything else...
////                .anyRequest().authenticated();
//
//        http.formLogin()//
//                .defaultSuccessUrl("/")//
//                .loginPage("/login")//
//                .permitAll();
//
//        http.logout().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {

    }
}