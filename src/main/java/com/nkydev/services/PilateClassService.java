package com.nkydev.services;

import com.nkydev.DTOs.pilateclass.PilateClassResponseDTO;
import com.nkydev.entities.PilateClass;
import com.nkydev.repositories.PilateClassRepository;
import org.springframework.stereotype.Service;

@Service
public class PilateClassService {
    final private PilateClassRepository pilateClassRepository;

    public PilateClassService(PilateClassRepository pilateClassRepository) {
        this.pilateClassRepository = pilateClassRepository;
    }

    public PilateClassResponseDTO mapToPilateClass(PilateClass pilateClass){
        return new PilateClassResponseDTO(
                pilateClass.getId(),
                pilateClass.getName(),
                pilateClass.getDescription(),
                pilateClass.getDurationMinutes(),
                pilateClass.getMaxCapacity()
        );
    }
}
