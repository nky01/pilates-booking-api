package com.nkydev.controllers;

import com.nkydev.DTOs.booking.BookingRequestDTO;
import com.nkydev.DTOs.booking.BookingResponseDTO;
import com.nkydev.services.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponseDTO createBooking(@RequestBody BookingRequestDTO request){
        return bookingService.createBooking(request);
    }
}