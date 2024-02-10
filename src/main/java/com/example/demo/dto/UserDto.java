package com.example.demo.dto;

import com.example.demo.util.Constants;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserDto {

    private Long id;

    @NotBlank
    private String firstName;
    private String middleName;
    @NotBlank
    private String lastName;

    @NotBlank
    @Email(message = "Please provide a valid email address")
    @Pattern(regexp = Constants.EMAIL_REGEX, message = "Enter email in valid format")
    private String email;

    @NotBlank
    @Pattern(regexp = Constants.MOBILE_NUMBER_REGEX, message = "Must have valid format with country code e.g. +639270001234")
    private String mobileNumber;

    public UserDto() {
    }

    public UserDto(
            Long id,
            String firstName,
            String middleName,
            String lastName,
            String mobileNumber,
            String email) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.email = email;

    }

    public UserDto(
            String firstName,
            String middleName,
            String lastName,
            String mobileNumber,
            String email) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.email = email;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

}
