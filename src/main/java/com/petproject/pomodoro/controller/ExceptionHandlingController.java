package com.petproject.pomodoro.controller;

import com.petproject.pomodoro.exceptions.BadRequestException;
import com.petproject.pomodoro.exceptions.SuchElementAlreadyExistsException;
import com.petproject.pomodoro.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler({BadRequestException.class, IllegalArgumentException.class})
    public ResponseEntity<Void> handleBadRequestException() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }

    @ExceptionHandler({UserNotFoundException.class, BadCredentialsException.class})
    public ResponseEntity<Void> handleUserNotFoundException() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @ExceptionHandler(SuchElementAlreadyExistsException.class)
    public ResponseEntity<Void> handleSuchElementAlreadyExistsException() {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .build();
    }
}
