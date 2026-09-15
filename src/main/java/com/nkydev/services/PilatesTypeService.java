package com.nkydev.services;

import com.nkydev.DTOs.pilatestype.PilatesTypeRequestDTO;
import com.nkydev.DTOs.pilatestype.PilatesTypeResponseDTO;
import com.nkydev.entities.PilatesType;
import com.nkydev.repositories.PilatesTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PilatesTypeService {
    final private PilatesTypeRepository pilatesTypeRepository;

    public PilatesTypeService(PilatesTypeRepository pilatesTypeRepository) {
        this.pilatesTypeRepository = pilatesTypeRepository;
    }



    public PilatesTypeResponseDTO mapToPilateClass(PilatesType pilatesType){
        return new PilatesTypeResponseDTO(
                pilatesType.getId(),
                pilatesType.getName(),
                pilatesType.getDurationMinutes(),
                pilatesType.getMaxCapacity()
        );
    }

    public PilatesTypeResponseDTO createPilatesType(PilatesTypeRequestDTO request) {
    }

    public List<PilatesTypeResponseDTO> getAllPilatesType() {
        return null;
    }

    public PilatesTypeResponseDTO getPilatesTypeById(Long id) {
    }

    public PilatesTypeResponseDTO updatePilatesType(PilatesTypeRequestDTO request) {
    }

    public void deletePilatesType() {
    }
}
