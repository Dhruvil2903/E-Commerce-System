package com.dhruvil.auth_service.customExceptions;

public class InvalidCredentialException extends Exception{
    public InvalidCredentialException(String message){
        super(message);
    }
}
