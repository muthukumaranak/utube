package com.app.youtubeclone.service;

import com.app.youtubeclone.entity.Users;
import org.springframework.security.core.userdetails.UserDetailsService;



public interface UserService extends UserDetailsService
{
    Users save(Users users);
    public String register(String name, String email, String password);
}