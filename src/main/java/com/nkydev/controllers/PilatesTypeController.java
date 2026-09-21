package com.nkydev.controllers;

import com.nkydev.DTOs.pilatestype.PilatesTypeRequestDTO;
import com.nkydev.DTOs.pilatestype.PilatesTypeResponseDTO;
import com.nkydev.services.PilatesTypeService;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pilates_type")
public class PilatesTypeController {

    final private PilatesTypeService pilatesTypeService;

    public PilatesTypeController(PilatesTypeService pilatesTypeService) {
        this.pilatesTypeService = pilatesTypeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PilatesTypeResponseDTO createPilatesType(@RequestBody PilatesTypeRequestDTO request){
        return pilatesTypeService.createPilatesType(request);
    }

    @GetMapping
    public List<PilatesTypeResponseDTO> getAllPilatesType(){
        return pilatesTypeService.getAllPilatesType();
    }

    @GetMapping("/{id}")
    public PilatesTypeResponseDTO getPilatesTypeById(@PathVariable Long id){
        return pilatesTypeService.getPilatesTypeById(id);
    }

    @PutMapping("/{id}")
    public PilatesTypeResponseDTO updatePilatesType(@PathVariable Long id, @RequestBody PilatesTypeRequestDTO request){
        return pilatesTypeService.updatePilatesType(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePilatesType(@PathVariable Long id){
        pilatesTypeService.deletePilatesType(id);
    }
}