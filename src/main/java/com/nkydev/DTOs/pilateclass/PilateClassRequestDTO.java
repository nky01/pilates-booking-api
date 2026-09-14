package com.nkydev.DTOs.pilateclass;

public record PilateClassRequestDTO(
        String name,
        String description,
        Integer durationMinutes,
        Integer maxCapacity
) {
}
