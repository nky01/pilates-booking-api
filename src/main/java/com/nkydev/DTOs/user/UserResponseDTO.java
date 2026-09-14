package com.nkydev.DTOs.user;

import com.nkydev.enums.Role;

import java.time.LocalDateTime;

public record UserResponseDTO(
        Long id,
        String fullName,
        String email,
        String phone,
        Role role,
        LocalDateTime createdAt
        // for students
        // BookingResponseDTO booking
) {}