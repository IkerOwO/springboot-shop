package com.iker.shopBackend.exceptions;

public class UserDontExistsException extends RuntimeException{
    public UserDontExistsException(String message) {
        super(message);
    }
}
