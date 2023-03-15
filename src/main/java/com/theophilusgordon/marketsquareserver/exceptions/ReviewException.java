package com.theophilusgordon.marketsquareserver.exceptions;

public class ReviewException extends RuntimeException {
    public ReviewException(String message) {
        super(message);
    }

    public static String reviewNotFoundException(String id) {
        return "Review with id " + id + " not found";
    }
}
