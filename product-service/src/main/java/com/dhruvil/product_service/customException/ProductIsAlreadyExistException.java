package com.dhruvil.product_service.customException;

public class ProductIsAlreadyExistException extends Exception {

    public ProductIsAlreadyExistException(String message) {
        super(message);
    }
}
