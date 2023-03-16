package com.theophilusgordon.marketsquareserver.exception;

public class UserException extends RuntimeException{
    public UserException(String message) {
        super(message);
    }

    public static String userNotFoundException(String id) {
        return "User with id " + id + " not found";
    }
}
