package com.app.youtubeclone.service.impl;

import com.app.youtubeclone.entity.Users;
import com.app.youtubeclone.repository.UsersRepo;
import com.app.youtubeclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UsersRepo usersRepo) {
        super();
        this.usersRepo = usersRepo;
    }

    public UserServiceImpl() {
    }

    @Override
    public Users save(Users users) {
        Users user = new Users(users.getName(),users.getEmail(),
                bCryptPasswordEncoder.encode(users.getPassword()));
        user.setRole(user.getRole());
        user.setStatus(user.getStatus());
        return usersRepo.save(user);
    }

    public String register(String name, String email, String password) {
        try {
            Users users = new Users(name,email,bCryptPasswordEncoder.encode(password));
            users.setRole("user");
            users.setStatus("false");
            usersRepo.save(users);
        }
        catch (Exception e){
            System.out.println(e);
            return "negative";
        }
        return "redirect:/";
    }

    public String loginerror(){
        return "redirect:/";
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = usersRepo.findByEmail(email);
        if(user == null){
            System.out.println("Wrong Credentials");
            loginerror();
        }
        return new User(user.getEmail(),user.getPassword(),mapRolesToAuthorities(user));
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Users users){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(users.getRole()));
        return list;
    }
}
