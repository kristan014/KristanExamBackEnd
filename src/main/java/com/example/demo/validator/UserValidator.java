package com.example.demo.validator;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource ms;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.isAssignableFrom(clazz);

    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto) target;

        // mobile number length must be 13
        if (userDto.getMobileNumber().length() != 13) {
            errors.rejectValue("mobileNumber",
                    ms.getMessage("msg-property.length", new String[] { "Mobile Number", "13" }, Locale.US));
        }

        // unique email
        if(!userService.getUserByEmailAndIdNot(userDto.getEmail(), userDto.getId()).isEmpty()){
            errors.rejectValue("email",
                    ms.getMessage("msg-property.inused", new String[] { "email" }, Locale.US));
        }

        // unique mobile number
        if(!userService.getUserByMobileNumberAndIdNot(userDto.getMobileNumber(), userDto.getId()).isEmpty()){
            errors.rejectValue("mobileNumber",
                    ms.getMessage("msg-property.inused", new String[] { "mobile number" }, Locale.US));
        }
    }

}
