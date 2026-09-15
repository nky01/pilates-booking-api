package com.nkydev.services;

import com.nkydev.DTOs.pilatestype.PilatesTypeRequestDTO;
import com.nkydev.DTOs.pilatestype.PilatesTypeResponseDTO;
import com.nkydev.entities.PilatesType;
import com.nkydev.repositories.PilatesTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PilatesTypeService {
    final private PilatesTypeRepository pilatesTypeRepository;

    public PilatesTypeService(PilatesTypeRepository pilatesTypeRepository) {
        this.pilatesTypeRepository = pilatesTypeRepository;
    }

    public PilatesTypeResponseDTO createPilatesType(PilatesTypeRequestDTO request) {

        if(pilatesTypeRepository.existsByName(request.name())){
            throw new RuntimeException("Pilates type already exists with name: " + request.name());
        }

        PilatesType pilatesType = new PilatesType();
        pilatesType.setName(request.name());
        pilatesType.setDurationMinutes(request.durationMinutes());
        pilatesType.setMaxCapacity(request.maxCapacity());

        PilatesType pilatesTypeSave = pilatesTypeRepository.save(pilatesType);
        return mapToPilatesType(pilatesTypeSave);
    }

    @Transactional(readOnly = true)
    public List<PilatesTypeResponseDTO> getAllPilatesType() {
        return pilatesTypeRepository.findAll()
                .stream()
                .map(this::mapToPilatesType)
                .toList();
    }

    @Transactional(readOnly = true)
    public PilatesTypeResponseDTO getPilatesTypeById(Long id) {
        PilatesType pilatesType = pilatesTypeRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Pilates type not found with ID: " + id));

        return mapToPilatesType(pilatesType);
    }

    @Transactional
    public PilatesTypeResponseDTO updatePilatesType(Long id, PilatesTypeRequestDTO request) {
        PilatesType pilatesType = pilatesTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pilates type not found with ID: " + id));

        pilatesType.setName(request.name());
        pilatesType.setDurationMinutes(request.durationMinutes());
        pilatesType.setMaxCapacity(request.maxCapacity());

        return mapToPilatesType(pilatesType);
    }

    public void deletePilatesType(Long id) {
        if(!pilatesTypeRepository.existsById(id)){
            throw new RuntimeException("Pilates type not found with ID: " + id);
        }
        pilatesTypeRepository.deleteById(id);
    }

    private PilatesTypeResponseDTO mapToPilatesType(PilatesType pilatesType){
        return new PilatesTypeResponseDTO(
                pilatesType.getId(),
                pilatesType.getName(),
                pilatesType.getDurationMinutes(),
                pilatesType.getMaxCapacity()
        );
    }
}
