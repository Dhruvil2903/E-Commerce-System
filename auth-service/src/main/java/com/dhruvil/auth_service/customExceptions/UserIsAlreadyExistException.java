package com.dhruvil.auth_service.customExceptions;

public class UserIsAlreadyExistException extends Exception{

    public UserIsAlreadyExistException(String message){
        super(message);
    }
}
