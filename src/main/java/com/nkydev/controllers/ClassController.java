package com.nkydev.controllers;

import com.nkydev.DTOs.classSession.ClassSessionRequestDTO;
import com.nkydev.DTOs.classSession.ClassSessionResponseDTO;
import com.nkydev.services.ClassSessionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/classes/")
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
    public List<ClassSessionResponseDTO> getClasses(){
        return classSessionService.getAllClasses();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClass(@PathVariable Long id){
        classSessionService.deleteClass(id);
    }
}