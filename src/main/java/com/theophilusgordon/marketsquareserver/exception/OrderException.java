package com.theophilusgordon.marketsquareserver.exception;

public class OrderException extends RuntimeException{
    public OrderException(String message) {
        super(message);
    }

    public static String orderNotFoundException(String id) {
        return "Order with id " + id + " not found";
    }
}
