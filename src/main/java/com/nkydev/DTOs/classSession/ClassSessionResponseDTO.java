package com.nkydev.DTOs.classSession;

import com.nkydev.enums.ClassStatus;

import java.sql.Time;
import java.util.Date;

public record ClassSessionResponseDTO(
        Long id,
        Date date,
        Time startTime,
        Time endTime,
        Integer machines,
        ClassStatus status
) {}