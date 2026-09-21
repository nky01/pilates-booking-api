package com.nkydev.services;

import com.nkydev.DTOs.booking.BookingRequestDTO;
import com.nkydev.DTOs.booking.BookingResponseDTO;
import com.nkydev.entities.Booking;
import com.nkydev.entities.ClassSession;
import com.nkydev.entities.User;
import com.nkydev.enums.BookingStatus;
import com.nkydev.enums.Role;
import com.nkydev.repositories.BookingRepository;
import com.nkydev.repositories.ClassSessionRepository;
import com.nkydev.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ClassSessionRepository classSessionRepository;

    public BookingService(BookingRepository bookingRepository, UserRepository userRepository, ClassSessionRepository classSessionRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.classSessionRepository = classSessionRepository;
    }

    public BookingResponseDTO createBooking(BookingRequestDTO request) {

        User student = userRepository.findById(request.studentId())
                .orElseThrow(()-> new RuntimeException("User not found with ID: " + request.studentId()));

        ClassSession classSession = classSessionRepository.findById(request.classSessionId())
                .orElseThrow(()-> new RuntimeException("Class not found with DI: " + request.classSessionId()));

        if(student.getRole() != Role.STUDENT){
            throw new RuntimeException("Only user with STUDENT role can make a bookig");
        }

        boolean isBooked = bookingRepository.existsByStudentIdAndClassSessionIdAndStatus(
                student.getId(),
                classSession.getId(),
                BookingStatus.CONFIRMED
        );
        if(isBooked){
            throw new RuntimeException("The student has already booked this class session");
        }

        boolean hasOverlap = bookingRepository.hasStudentOverlappingBooking(
                student.getId(),
                classSession.getDate(),
                classSession.getStartTime(),
                classSession.getEndTime(),
                BookingStatus.CONFIRMED
        );
        if (hasOverlap) {
            throw new RuntimeException("Student already has another class booked in this time slot");
        }

        long activeBookings = bookingRepository.countByClassSessionIdAndStatus(
                classSession.getId(),
                BookingStatus.CONFIRMED
        );

        if (activeBookings >= classSession.getMachines()){
            throw new RuntimeException("No machines for this class session");
        }

        Booking booking = new Booking();
        booking.setDate(LocalDate.now());
        booking.setTime(LocalTime.now());
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setStudent(student);
        booking.setClassSession(classSession);

        Booking savedBooking = bookingRepository.save(booking);

        return mapToBooking(savedBooking);
    }

    public BookingResponseDTO mapToBooking(Booking booking){
        return new BookingResponseDTO(
                booking.getId(),
                booking.getDate(),
                booking.getTime(),
                booking.getStatus(),
                booking.getStudent().getId(),
                booking.getClassSession().getId()
        );
    }
}