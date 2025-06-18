package com.example.blog.response;

public class ApiErrorMessage {
    private String message;
    private int status;

    public ApiErrorMessage(String message, int status) {
        this.message = message;
        this.status = status;
    }
}