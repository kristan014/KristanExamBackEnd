package com.example.demo.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class BindingErrors {
    
    public static void processBindingErrors(BindingResult bindingResult, ResponseMessage responseMessage) {
        responseMessage.setStatusCode("401");
        List<ErrorResponse> errorResponses = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            ErrorResponse errorResponse = createErrorResponse(fieldError);
            errorResponses.add(errorResponse);
        }
        responseMessage.setErrorResponses(errorResponses);
    }

    public static ErrorResponse createErrorResponse(FieldError fieldError) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setColumn(fieldError.getField());
        errorResponse.setDetails(
                fieldError.getDefaultMessage() != null ? fieldError.getDefaultMessage() : fieldError.getCode());
        return errorResponse;
    }
}
