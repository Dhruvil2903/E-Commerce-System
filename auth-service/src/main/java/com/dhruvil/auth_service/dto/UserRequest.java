package com.dhruvil.auth_service.dto;

import com.dhruvil.auth_service.enumRole.Role;

public record UserRequest(String username, String password, Role role) {


}
