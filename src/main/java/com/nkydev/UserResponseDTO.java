package com.nkydev;

import com.nkydev.entities.Booking;
import com.nkydev.enums.Role;

import java.time.LocalDateTime;
import java.util.List;

public record UserResponseDTO(
        Long id,
        String fullName,
        String email,
        String phone,
        Role role,
        LocalDateTime createdAt
        // for students
        // BookingResponseDTO bookin
) {}