package com.dhruvil.auth_service.customExceptions;


public class UserIsNotFoundException extends Exception{

    public UserIsNotFoundException(String message){
        super(message);
    }
}
