package com.example.rtu.service;


import com.example.rtu.entity.Role;
import com.example.rtu.entity.User;
import com.example.rtu.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public User addUser(String name, String email, String password, String role){
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(Role.instructor);
        if(role.toLowerCase().equals("student")){
            user.setRole(Role.student);
        }
        return userRepo.save(user);
    }

    public String deleteUserById(long userId){

        User user = findUserById(userId);
        if(user==null){
            return  null;
        }else{
            userRepo.delete(user);
        }
        return "deleted";

    }

    public User findUserById(Long userId){
        Optional<User> user = userRepo.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        return  null;
    }

    public String updateUser(Long userId, String name, String email, String password, String role){
        User user = findUserById(userId);
        if(user==null){
            return null;
        }
        else{
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setRole(Role.instructor);
            if(role.toLowerCase().equals("student")){
                user.setRole(Role.student);
            }
            userRepo.save(user);
        }
        return "updated";
    }

    public List<User> getAllUsers(){
        return  userRepo.findAll();
    }


    public User findByEmail(String email){
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }



}
