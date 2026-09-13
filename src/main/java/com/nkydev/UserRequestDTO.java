package com.nkydev;

import com.nkydev.enums.Role;

public record UserRequestDTO(
        String fullName,
        String email,
        String password,
        String phone,
        Role role
) {}