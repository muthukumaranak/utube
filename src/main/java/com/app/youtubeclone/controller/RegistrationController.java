package com.app.youtubeclone.controller;

import com.app.youtubeclone.entity.Users;
import com.app.youtubeclone.repository.UsersRepo;
import com.app.youtubeclone.service.ChannelService;
import com.app.youtubeclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.PasswordAuthentication;

@Controller
public class RegistrationController {
    @Autowired
    private UsersRepo usersRepo;


    @Autowired
    UserService userService;

    @Autowired
    ChannelService channelService;


    @GetMapping("/signIn")
    public String signIn() {
        return "userRegistration";
    }

    @PostMapping("/userRegistration")
    public String userRegistration(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password") String password){
        return userService.register(name, email, password);
    }

    @PostMapping("/channel")
    public String channelRegistration(@RequestParam String name, @RequestParam String description,
                                      @RequestParam("thumbnail") MultipartFile thumbnail){
        return channelService.register(name, description, thumbnail);
    }
}