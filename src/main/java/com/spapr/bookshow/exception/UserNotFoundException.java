package com.spapr.bookshow.exception;


public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {

        super(String.format(message));
    }
}
