package com.dhruvil.auth_service.dto;

import com.dhruvil.auth_service.enumRole.Role;

public record UserResponse(Long id, String username, Role role) {
}
