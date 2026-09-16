package com.nkydev.controllers;

import com.nkydev.DTOs.classSession.ClassSessionRequestDTO;
import com.nkydev.DTOs.classSession.ClassSessionResponseDTO;
import com.nkydev.services.ClassSessionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/classes/")
public class ClassController {

    final private ClassSessionService classSessionService;

    public ClassController(ClassSessionService classSessionService) {
        this.classSessionService = classSessionService;
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClass(@PathVariable Long id){
        classSessionService.deleteClass(id);
    }
}