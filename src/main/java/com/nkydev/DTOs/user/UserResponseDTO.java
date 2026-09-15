package com.nkydev.DTOs.user;

import com.nkydev.enums.Role;

public record UserResponseDTO(
        Long id,
        String fullName,
        String email,
        String phone,
        Role role
) {}