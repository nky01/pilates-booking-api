package com.nkydev.DTOs.classSession;

import com.nkydev.enums.ClassStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public record ClassSessionResponseDTO(
        Long id,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        Integer machines,
        ClassStatus status
) {}