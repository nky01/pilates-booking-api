package com.nkydev.DTOs.pilatestype;

public record PilatesTypeResponseDTO(
        Long id,
        String name,
        Integer durationMinutes,
        Integer maxCapacity
) {}
