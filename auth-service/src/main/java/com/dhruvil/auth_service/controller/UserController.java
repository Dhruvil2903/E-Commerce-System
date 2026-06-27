package com.dhruvil.auth_service.controller;

import com.dhruvil.auth_service.customExceptions.InvalidCredentialException;
import com.dhruvil.auth_service.customExceptions.UserIsAlreadyExistException;
import com.dhruvil.auth_service.customExceptions.UserIsNotFoundException;
import com.dhruvil.auth_service.dto.LoginResponse;
import com.dhruvil.auth_service.dto.UserRequest;
import com.dhruvil.auth_service.dto.UserResponse;
import com.dhruvil.auth_service.serviceImpl.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest) throws UserIsAlreadyExistException {
        UserResponse userResponse = userService.register(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserRequest userRequest) throws UserIsNotFoundException, InvalidCredentialException {
        LoginResponse userResponse = userService.login(userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);

    }
}
