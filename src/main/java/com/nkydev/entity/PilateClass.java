package com.nkydev.entity;

import java.util.Objects;

public class PilateClass {
    private Integer id;
    private String name;
    private String description;
    private Integer durationMinutes;
    private Integer maxCapacity;

    public PilateClass(){}

    public PilateClass(Integer id, String name, String description, Integer durationMinutes, Integer maxCapacity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.durationMinutes = durationMinutes;
        this.maxCapacity = maxCapacity;
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
