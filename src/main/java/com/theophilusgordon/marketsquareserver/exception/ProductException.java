package com.theophilusgordon.marketsquareserver.exception;

import java.util.UUID;

public class ProductException extends RuntimeException{
    public ProductException(String message) {
        super(message);
    }

    public static String productNotFoundException(UUID id) {
        return "Product with id " + id + " not found";
    }
}
