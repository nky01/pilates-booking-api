package com.nkydev.DTOs.booking;

import com.nkydev.enums.BookingStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public record BookingRequestDTO(
      LocalDate date,
      LocalTime time,
      BookingStatus status,
      Long studentId,
      Long classSessionId
) {}