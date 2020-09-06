package com.app.youtubeclone.service;

import com.app.youtubeclone.entity.Users;
import com.app.youtubeclone.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MyUserDetailService  {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    public MyUserDetailService(UsersRepo usersRepo) {
        super();
        this.usersRepo = usersRepo;
    }
    public Users save(Users user) {
        Users users = new Users(user.getName(),user.getEmail(),
                bCryptPasswordEncoder.encode(user.getPassword()));
        users.setRole(user.getRole());
        users.setStatus(user.getStatus());
        return usersRepo.save(users);
    }
/*
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = usersRepo.findByEmail(email);
        if(user == null){
            throw  new UsernameNotFoundException("User does not exist");
        }
        System.out.println("ud called");
        return new User(user.getEmail(),user.getPassword(),mapRolesToAuthorities(user));
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Users users){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(users.getRole()));
        return list;
    } */
}