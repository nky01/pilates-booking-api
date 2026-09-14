package com.nkydev.DTOs.pilateclass;

public record PilateClassResponseDTO(
        Long id,
        String name,
        String description,
        Integer durationMinutes,
        Integer maxCapacity
        // ScheduleResponseDTO schedule
) {
}
