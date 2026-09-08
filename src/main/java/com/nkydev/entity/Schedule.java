package com.nkydev.entity;

import com.nkydev.enums.ScheduleStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "schedules")
public class Schedule { // turnos programados
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer availableSlots;
    private ScheduleStatus status;

    @OneToMany
    private PilateClass pilatesClass;

    @OneToMany
    private List<Booking> bookings = new ArrayList<>();

    public Schedule() {}

    public Schedule(Long id, LocalDateTime startTime, LocalDateTime endTime, Integer availableSlots, ScheduleStatus status, PilateClass pilatesClass, List<Booking> bookings) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.availableSlots = availableSlots;
        this.status = status;
        this.pilatesClass = pilatesClass;
        this.bookings = bookings;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    public Integer getAvailableSlots() { return availableSlots; }
    public void setAvailableSlots(Integer availableSlots) { this.availableSlots = availableSlots; }

    public ScheduleStatus getStatus() { return status; }
    public void setStatus(ScheduleStatus status) { this.status = status; }

    public PilateClass getPilatesClass() { return pilatesClass; }
    public void setPilatesClass(PilateClass pilatesClass) { this.pilatesClass = pilatesClass; }

    public List<Booking> getBookings() { return bookings; }
    public void setBookings(List<Booking> bookings) { this.bookings = bookings; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Schedule that = (Schedule) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}