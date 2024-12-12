package com.practice.Dockerize.controllers;

import com.practice.Dockerize.entities.User;

import com.practice.Dockerize.services.UserService;
import com.practice.Dockerize.DTOS.UserRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping
    public String createUser(@RequestBody User user){
        return service.createUser(user);
    }

    @PostMapping("/discover")
    public String userexists(@RequestBody UUID id){

        return service.users(id);

    }
    @GetMapping
    public List<User> users(){
        return service.getAllUSers();
    }
/*
    @PostMapping("/testPost")
    public User createUser(String name, String password,String email){
        User user = new User(UUID.randomUUID(),name, password,email);
        return service.createUsers(user);
    }*/

    /*@PostMapping("/increase")
    public ResponseEntity<Integer> increaseCount(@RequestBody UserRequest request) {
        try {
            Integer count = service.addCount(request.getName());
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            // Handle exceptions gracefully
            return ResponseEntity.badRequest().body(-1);
        }
    }*/
    /* @PostMapping("/increase")
    public Integer increaseCount(@RequestBody String name){
        return service.addCount(name);
    }*/


}
