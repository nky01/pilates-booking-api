package com.nkydev.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pilateclasses")
public class PilateClass { // los tipos de pilates
    private Integer id;
    private String name;
    private String description;
    private Integer durationMinutes;
    private Integer maxCapacity;

    @OneToMany
    private List<Schedule> schedules= new ArrayList<>();

    public PilateClass(){}

    public PilateClass(Integer id, String name, String description, Integer durationMinutes, Integer maxCapacity, List<Schedule> schedules) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.durationMinutes = durationMinutes;
        this.maxCapacity = maxCapacity;
        this.schedules = schedules;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PilateClass that = (PilateClass) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(durationMinutes, that.durationMinutes) && Objects.equals(maxCapacity, that.maxCapacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, durationMinutes, maxCapacity);
    }
}
