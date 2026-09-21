package com.nkydev.controllers;

import com.nkydev.DTOs.classSession.ClassSessionRequestDTO;
import com.nkydev.DTOs.classSession.ClassSessionResponseDTO;
import com.nkydev.enums.ClassStatus;
import com.nkydev.services.ClassSessionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classes")
public class ClassController {

    final private ClassSessionService classSessionService;

    public ClassController(ClassSessionService classSessionService) {
        this.classSessionService = classSessionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClassSessionResponseDTO createClass(@RequestBody ClassSessionRequestDTO request) {
        return classSessionService.createClass(request);
    }

    @GetMapping
    public List<ClassSessionResponseDTO> getAllClasses(){
        return classSessionService.getAllClasses();
    }

    @GetMapping("/{id}")
    public ClassSessionResponseDTO getClassById(@PathVariable Long id){
        return classSessionService.getClassById(id);
    }

    @GetMapping("/available")
    public List<ClassSessionResponseDTO> getAvailableClasses(){
        return classSessionService.getAvailableClasses();
    }

    @PutMapping("/{id}")
    public ClassSessionResponseDTO updateClass(@PathVariable Long id, @RequestBody ClassSessionRequestDTO request){
        return classSessionService.updateClass(id, request);
    }

    @PutMapping("/{id}/status")
    public ClassSessionResponseDTO updateClassStatus(@PathVariable Long id, @RequestParam ClassStatus newStatus) {
        return classSessionService.updateClassStatus(id, newStatus);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClass(@PathVariable Long id){
        classSessionService.deleteClass(id);
    }
}