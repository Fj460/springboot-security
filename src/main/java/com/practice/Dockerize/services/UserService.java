package com.practice.Dockerize.services;

import com.practice.Dockerize.entities.User;

import com.practice.Dockerize.repositories.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UsersRepository repository;

    public String createUser(User user){
        repository.save(user);
        return "user created1!";
    }
    public User createUsers(User user){
        return repository.save(user);
    }
    public String users(UUID id){
        User user= repository.findById(id).orElseThrow();
            if(user!=null){
                return "User Exists!";
            }
            return "User not found";
    }

    public List<User> getAllUSers(){
        return repository.findAll();
    }


/*
   public Integer addCount(String name){
        User user= repository.findByName(name);
        if(user==null){
            System.out.println("User not found "+ name);
            return 0;
        }
        user.setCount(user.getCount()+1);
        return user.getCount();
    }*/


}
