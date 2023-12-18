package com.example.demo.controller;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;


@RestController
@RequestMapping(path = "api/v1/users")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    // Get All
    @CrossOrigin
    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }


    // Get One
    @CrossOrigin
    @GetMapping(path="{userId}")
    public Object getUser(@PathVariable("userId") Long userId){
        return userService.getUser(userId);
    }


    @CrossOrigin
    @PostMapping
    public void registerNewUser(@RequestBody User user){
        userService.addNewUser(user);
    }

    @CrossOrigin
    @DeleteMapping(path="{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
    }

    @CrossOrigin
    @PutMapping(path="{userId}")
    public void putMethodName(@PathVariable Long userId, 
                              @RequestBody User user
                              ) {
      
    userService.updateUser(userId,user);
        
    }

}
