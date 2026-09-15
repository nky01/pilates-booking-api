package com.nkydev.DTOs.pilatestype;

public record PilatesTypeRequestDTO(
        String name,
        Integer durationMinutes,
        Integer maxCapacity
) {
}
