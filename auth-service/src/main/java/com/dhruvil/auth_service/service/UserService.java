package com.dhruvil.auth_service.service;

import com.dhruvil.auth_service.customExceptions.InvalidCredentialException;
import com.dhruvil.auth_service.customExceptions.UserIsAlreadyExistException;
import com.dhruvil.auth_service.customExceptions.UserIsNotFoundException;
import com.dhruvil.auth_service.dto.UserRequest;
import com.dhruvil.auth_service.dto.UserResponse;


public interface UserService {

    UserResponse register(UserRequest userRequest) throws UserIsAlreadyExistException;
    UserResponse login(UserRequest userRequest) throws UserIsNotFoundException, InvalidCredentialException;
}
