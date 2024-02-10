package com.example.demo.util;

import java.util.List;

public class ResponseMessage {

    private String statusCode;

    private String successResponse;

    private List<ErrorResponse> errorResponses;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getSuccessResponse() {
        return successResponse;
    }

    public void setSuccessResponse(String successResponse) {
        this.successResponse = successResponse;
    }

    public List<ErrorResponse> getErrorResponses() {
        return errorResponses;
    }

    public void setErrorResponses(List<ErrorResponse> errorResponses) {
        this.errorResponses = errorResponses;
    }

}
