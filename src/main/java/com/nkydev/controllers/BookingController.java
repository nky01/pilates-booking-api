package com.nkydev.controllers;

import com.nkydev.DTOs.booking.BookingRequestDTO;
import com.nkydev.DTOs.booking.BookingResponseDTO;
import com.nkydev.services.BookingService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<BookingResponseDTO> getAllBookings(){
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public BookingResponseDTO getBookingById(@PathVariable Long id){
        return bookingService.getBookingById(id);
    }

    @PutMapping("/{id}")
    public BookingResponseDTO updateBooking(@PathVariable Long id, @RequestBody BookingRequestDTO request){
        return bookingService.updateBooking(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooking(@PathVariable Long id){
        bookingService.deleteBooking(id);
    }

    @PatchMapping("/{id}/cancel")
    public BookingResponseDTO cancelBooking(Long id){
        return bookingService.cancelBooking(id);
    }
}