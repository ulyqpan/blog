package com.example.blog.exception;

import com.example.blog.response.ApiErrorMessage;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiErrorMessage> handleNotFound(NotFoundException e) {
        return new ResponseEntity<>(new ApiErrorMessage(e.getMessage(), 404), HttpStatus.NOT_FOUND);
    }
}
