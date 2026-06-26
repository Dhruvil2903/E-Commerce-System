package com.dhruvil.auth_service.serviceImpl;


import com.dhruvil.auth_service.customExceptions.InvalidCredentialException;
import com.dhruvil.auth_service.customExceptions.UserIsAlreadyExistException;
import com.dhruvil.auth_service.customExceptions.UserIsNotFoundException;
import com.dhruvil.auth_service.dto.UserRequest;
import com.dhruvil.auth_service.dto.UserResponse;
import com.dhruvil.auth_service.entity.User;
import com.dhruvil.auth_service.repository.UserRepository;
import com.dhruvil.auth_service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserResponse register(UserRequest userRequest) throws UserIsAlreadyExistException {

        Optional<User> userExist = userRepository.findByUsername(userRequest.username());

        if(userExist.isPresent()){
            throw new UserIsAlreadyExistException("User is already exist");
        }
        User user = new User();
        user.setUsername(userRequest.username());
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        user.setRole(userRequest.role());
        User savedUser = userRepository.save(user);
        return new UserResponse(savedUser.getId(),savedUser.getUsername(),savedUser.getRole());
    }

    @Override
    public UserResponse login(UserRequest userRequest) throws UserIsNotFoundException, InvalidCredentialException {
        Optional<User> userExist = userRepository.findByUsername(userRequest.username());
        if(userExist.isEmpty()){
            throw new UserIsNotFoundException("Credentials are invalid");
        }
       User user = userExist.get();

        if(!passwordEncoder.matches(userRequest.password(), user.getPassword())){
            throw new InvalidCredentialException("Credentials are invalid");
        }

        return new UserResponse(
                user.getId(), user.getUsername(),user.getRole()
        );
    }
}
