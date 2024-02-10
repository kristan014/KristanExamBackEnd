package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.repo.UserRepo;
import com.example.demo.util.FindUniqueItems;

import jakarta.transaction.Transactional;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    // get all contaning keyword of firstname and lastname
    public List<User> getNames(String keyword) {

        List<User> result = new ArrayList<>();

        // add all containing firstnames to created list
        List<User> firstNames = userRepo.findByFirstNameStartingWithIgnoreCase(keyword);
        result.addAll(firstNames);

        // add all containing lastnames to created list
        List<User> laststNames = userRepo.findByLastNameStartingWithIgnoreCase(keyword);
        result.addAll(laststNames);

        List<User> uniqueItems = FindUniqueItems.findUniqueItems(result);

        return uniqueItems;
    }

    // get all
    public List<User> getUsers() {
        return userRepo.findByOrderByIdAsc();
    }

    // create
    public void addNewUser(UserDto userDto) {
        User userEmail = userRepo.findUserByEmail(userDto.getEmail());
        User userMobileNumber = userRepo.findUserByMobileNumber(userDto.getMobileNumber());

        User user = convertToUser(userDto);

        if (userEmail != null) {
            throw new IllegalStateException("Email taken");
        }

        if (userMobileNumber != null) {
            throw new IllegalStateException("Mobile number taken");
        }

        userRepo.save(user);

    }

    // update
    @Transactional
    public void updateUser(Long userId, UserDto userDto) {

        User _user = userRepo.findById(userId).orElseThrow(
                () -> new IllegalStateException("user with the id of " + userId + "does not exists"));

        _user.setFirstName(userDto.getFirstName());
        _user.setMiddleName(userDto.getMiddleName());
        _user.setLastName(userDto.getLastName());
        _user.setEmail(userDto.getEmail());
        _user.setMobileNumber(userDto.getMobileNumber());

        userRepo.save(_user);
    }

    // delete By id
    public void deleteUser(Long userId) {
        boolean exists = userRepo.existsById(userId);

        if (!exists) {
            throw new IllegalStateException("user with the id of " + userId + "does not exists");
        }
        userRepo.deleteById(userId);
    }

    // find one by id
    public Object getUser(Long id) {
        return userRepo.findById(id);
    }

    // find one by email
    public List<User> getUserByEmailAndIdNot(String email, Long id) {
        return userRepo.findByEmailWhereIdNotEqual(email, id);
    }

    // find one by mobile number
    public List<User> getUserByMobileNumberAndIdNot(String mobileNumber, Long id) {
        return userRepo.findByMobileNumberWhereIdNotEqual(mobileNumber, id);
    }

    // convert dto to entity
    private User convertToUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setMiddleName(userDto.getMiddleName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setMobileNumber(userDto.getMobileNumber());

        return user;
    }

}
