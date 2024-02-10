package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.util.BindingErrors;
import com.example.demo.util.ResponseMessage;
import com.example.demo.validator.UserValidator;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "api/v1/users")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserService userService;


    // get all user
    @CrossOrigin
    @GetMapping
    public List<User> getUsers(@RequestParam(name = "name", required = false) String name) {
        if (name == null) {
            return userService.getUsers();
        } else {
            return userService.getNames(name);
        }

    }

    // get one user
    @CrossOrigin
    @GetMapping(path = "{userId}")
    public Object getUser(@PathVariable("userId") Long userId) {
        return userService.getUser(userId);
    }

    // create user
    @CrossOrigin
    @PostMapping
    public ResponseEntity<ResponseMessage> registerNewUser(@Valid @RequestBody UserDto userDto,
            BindingResult bindingResult) {

        ResponseMessage responseMessage = new ResponseMessage();

        if (bindingResult.hasErrors()) {
            BindingErrors.processBindingErrors(bindingResult, responseMessage);
        } else {
            addUserAndSetSuccessResponse(userDto, responseMessage);
        }

        return ResponseEntity.ok(responseMessage);

    }

    // delete user
    @CrossOrigin
    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }

    // update user
    @CrossOrigin
    @PutMapping(path = "{userId}")
    public ResponseEntity<ResponseMessage> updateUser(@PathVariable Long userId,
            @Valid @RequestBody UserDto userDto, BindingResult bindingResult) {

                
        ResponseMessage responseMessage = new ResponseMessage();        
        userValidator.validate(userDto, bindingResult);
        
        if (bindingResult.hasErrors()) {
            BindingErrors.processBindingErrors(bindingResult, responseMessage);
        } else {
            updateUserAndSetSuccessResponse(userId, userDto, responseMessage);
        }

        return ResponseEntity.ok(responseMessage);

    }

    private void updateUserAndSetSuccessResponse(Long userId, UserDto userDto, ResponseMessage responseMessage) {
        userService.updateUser(userId, userDto);
        responseMessage.setStatusCode("200");
        responseMessage.setSuccessResponse("User is successfully updated");
    }

    private void addUserAndSetSuccessResponse( UserDto userDto, ResponseMessage responseMessage) {
        userService.addNewUser(userDto);
        responseMessage.setStatusCode("200");
        responseMessage.setSuccessResponse("User is successfully updated");
    }
}
