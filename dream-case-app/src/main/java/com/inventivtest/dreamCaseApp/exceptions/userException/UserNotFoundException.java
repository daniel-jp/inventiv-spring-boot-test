package com.inventivtest.dreamCaseApp.exceptions.userException;
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}