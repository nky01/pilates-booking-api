package com.nkydev.DTOs.classSession;

import java.time.LocalDate;
import java.time.LocalTime;

public record ClassSessionRequestDTO(
         LocalDate date,
         LocalTime startTime,
         LocalTime endTime,
         Integer machines,
         Long pilatesTypeId,
         Long teacherId
) {}
