package com.nkydev.repositories;

import com.nkydev.entities.ClassSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ClassSessionRepository extends JpaRepository<ClassSession, Long> {
    boolean existsByTeacherIdAndDateAndStartTimeLessThanAndEndTimeGreaterThan(
            Long teacherId,
            LocalDate date,
            LocalTime endTime,
            LocalTime startTime
    );
}