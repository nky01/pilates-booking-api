package com.nkydev.entities;

import com.nkydev.enums.ClassStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "classes")
public class ClassSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private Integer machines;

    @Enumerated(EnumType.STRING)
    private ClassStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pilate_class_id", nullable = false)
    private PilatesType pilatesType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private User teacher;

    public ClassSession() {}

    public ClassSession(Long id, LocalDate date, LocalTime startTime, LocalTime endTime, Integer machines, ClassStatus status, PilatesType pilatesType, User teacher) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.machines = machines;
        this.status = status;
        this.pilatesType = pilatesType;
        this.teacher = teacher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Integer getMachines() {
        return machines;
    }

    public void setMachines(Integer machines) {
        this.machines = machines;
    }

    public ClassStatus getStatus() {
        return status;
    }

    public void setStatus(ClassStatus status) {
        this.status = status;
    }

    public PilatesType getPilatesType() {
        return pilatesType;
    }

    public void setPilatesType(PilatesType pilatesType) {
        this.pilatesType = pilatesType;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ClassSession that = (ClassSession) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}