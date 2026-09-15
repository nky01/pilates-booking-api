package com.nkydev.repositories;

import com.nkydev.entities.PilatesType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PilatesTypeRepository extends JpaRepository<PilatesType, Long> {
    Boolean existsByName(String name);
}