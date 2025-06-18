package com.example.blog.response;

import java.util.List;

public class IamResponse<T> {
    private T data;
    private String message;
    private boolean success;

    public IamResponse(T data, String message, boolean success) {
        this.data = data;
        this.message = message;
        this.success = success;
    }

    public static <T> IamResponse<T> success(T data) {
        return new IamResponse<>(data, "OK", true);
    }

    public static <T> IamResponse<T> failure(String message) {
        return new IamResponse<>(null, message, false);
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
