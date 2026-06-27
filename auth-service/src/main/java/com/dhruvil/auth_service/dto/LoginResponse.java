package com.dhruvil.auth_service.dto;

import com.dhruvil.auth_service.enumRole.Role;

public record LoginResponse(Long id, String username, Role role, String token) {
}
