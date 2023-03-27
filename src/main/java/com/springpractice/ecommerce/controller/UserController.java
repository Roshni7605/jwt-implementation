package com.springpractice.ecommerce.controller;

import com.springpractice.ecommerce.entity.User;
import com.springpractice.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRolesAndUser(){
        userService.initRolesAndUser();
    }

    @GetMapping("{/hello}")
//    @PreAuthorize("hasRole('User')")
    public String hello(){
        return "This URL is only accessible to user";
    }


    @PostMapping({"/registerUser"})
    public User registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to user";
    }
}
