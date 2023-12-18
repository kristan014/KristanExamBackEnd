package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.repo.UserRepo;

import jakarta.transaction.Transactional;

import com.example.demo.model.User;

@Service
public class UserService {

    @Autowired
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    // Get All
    public List<User> getUsers() {

        return userRepo.findAll();
    }


    // Create
    public void addNewUser(User user) {
        Optional<User> userEmail = userRepo.findUserByEmail(user.getEmail());
        Optional<User> userMobileNumber = userRepo.findUserByEmail(user.getMobileNUmber());


        if (userEmail.isPresent()) {
            throw new IllegalStateException("email taken");
        }

        if (userMobileNumber.isPresent()) {
            throw new IllegalStateException("mobile number taken");
        }

        System.out.print(user);
        userRepo.save(user);

    }

  // Update
    @Transactional
       public void updateUser(Long userId, User user) {

        User _user = userRepo.findById(userId).orElseThrow(
                () -> new IllegalStateException("user with the id of " + userId + "does not exists"));
        
            
        _user.setFirstName(user.getFirstName());
        _user.setMiddleName(user.getMiddleName());
        _user.setLastName(user.getLastName());
        _user.setEmail(user.getEmail());
        _user.setMobileNumber(user.getMobileNUmber());


        userRepo.save(user);
    }

    // Delete By
    public void deleteUser(Long userId) {
        boolean exists = userRepo.existsById(userId);

        if (!exists) {
            throw new IllegalStateException("user with the id of " + userId + "does not exists");
        }
        userRepo.deleteById(userId);
    }

    //Find One
    public Object getUser(Long id) {
            return userRepo.findById(id);
    }
}
