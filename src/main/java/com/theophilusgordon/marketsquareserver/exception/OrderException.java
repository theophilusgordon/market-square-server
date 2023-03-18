package com.theophilusgordon.marketsquareserver.exception;

public class OrderException extends RuntimeException{
    public OrderException(String message) {
        super(message);
    }
}
