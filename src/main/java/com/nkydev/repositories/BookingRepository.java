package com.nkydev.repositories;

import com.nkydev.entities.Booking;
import com.nkydev.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    boolean existsByStudentIdAndClassSessionIdAndStatus(
            Long studentId,
            Long classSessionId,
            BookingStatus status
    );

    @Query("""
        SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END
        FROM Booking b
        WHERE b.student.id = :studentId
          AND b.status = :status
          AND b.classSession.date = :date
          AND b.classSession.startTime < :newEndTime
          AND b.classSession.endTime > :newStartTime
    """)
    boolean hasStudentOverlappingBooking(
            @Param("studentId") Long studentId,
            @Param("date") LocalDate date,
            @Param("newStartTime") LocalTime newStartTime,
            @Param("newEndTime") LocalTime newEndTime,
            @Param("status") BookingStatus status
    );

    long countByClassSessionIdAndStatus(
            Long classSessionId,
            BookingStatus status);
}
