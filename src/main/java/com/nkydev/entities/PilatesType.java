package com.nkydev.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pilates_type")
public class PilatesType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private Integer durationMinutes;

    @Column(nullable = false)
    private Integer maxCapacity;

    public PilatesType(){}

    public PilatesType(Long id, String name, Integer durationMinutes, Integer maxCapacity) {
        this.id = id;
        this.name = name;
        this.durationMinutes = durationMinutes;
        this.maxCapacity = maxCapacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PilatesType that = (PilatesType) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(durationMinutes, that.durationMinutes) && Objects.equals(maxCapacity, that.maxCapacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, durationMinutes, maxCapacity);
    }
}
