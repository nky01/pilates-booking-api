package com.nkydev.repositories;

import com.nkydev.entities.ClassSession;
import com.nkydev.enums.ClassStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ClassSessionRepository extends JpaRepository<ClassSession, Long> {

    @Query("""
            SELECT COUNT(c) > 0 
            FROM ClassSession c 
            WHERE c.teacher.id = :teacherId 
                AND c.date = :date 
                AND c.startTime < :endTime 
                AND c.endTime > :startTime
                AND c.status != 'CANCELLED'
    """)
    boolean existByTeacherInClassSession(
            @Param("teacherId") Long teacherId,
            @Param("date") LocalDate date,
            @Param("endTime") LocalTime endTime,
            @Param("startTime") LocalTime startTime
    );

    @Query("""
           SELECT c
           FROM ClassSession c
           WHERE c.status = :status
           AND (c.date > :currentDate OR (c.date = :currentDate AND c.startTime > :currentTime))
           AND (c.machines - (
            SELECT COUNT(b)\s
            FROM Booking b\s
            WHERE b.classSession.id = c.id\s
                AND b.status = 'CONFIRMED'
            )) > 0
    """)
    List<ClassSession> findAvailableClass(@Param("status")ClassStatus status, @Param("currentDate") LocalDate currentDate, @Param("currentTime") LocalTime currentTime);
}