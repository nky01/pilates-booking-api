package com.nkydev.entity;

import com.nkydev.enums.BookingStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "bookings")
public class Booking {
    private Integer id;
    private LocalDateTime bookingDate;
    private BookingStatus status;

    public Booking(){}

    public Booking(Integer id, LocalDateTime bookingDate, BookingStatus status) {
        this.id = id;
        this.bookingDate = bookingDate;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id) && Objects.equals(bookingDate, booking.bookingDate) && status == booking.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookingDate, status);
    }
}
