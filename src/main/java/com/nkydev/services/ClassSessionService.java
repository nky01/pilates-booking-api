package com.nkydev.services;

import com.nkydev.DTOs.classSession.ClassSessionRequestDTO;
import com.nkydev.DTOs.classSession.ClassSessionResponseDTO;
import com.nkydev.entities.ClassSession;
import com.nkydev.entities.PilatesType;
import com.nkydev.entities.User;
import com.nkydev.enums.ClassStatus;
import com.nkydev.enums.Role;
import com.nkydev.repositories.ClassSessionRepository;
import com.nkydev.repositories.PilatesTypeRepository;
import com.nkydev.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClassSessionService {

    private final ClassSessionRepository classSessionRepository;
    private final UserRepository userRepository;
    private final PilatesTypeRepository pilatesTypeRepository;

    public ClassSessionService(ClassSessionRepository classSessionRepository, UserRepository userRepository, PilatesTypeRepository pilatesTypeRepository) {
        this.classSessionRepository = classSessionRepository;
        this.userRepository = userRepository;
        this.pilatesTypeRepository = pilatesTypeRepository;
    }

    public ClassSessionResponseDTO createClass(ClassSessionRequestDTO request){

        User teacher = userRepository.findById(request.teacherId())
                .orElseThrow(()-> new RuntimeException("Teacher not found with ID: " + request.teacherId()));

        if (teacher.getRole() != Role.TEACHER){
            throw new RuntimeException("The user isn't a TEACHER");
        }

        PilatesType pilatesType = pilatesTypeRepository.findById(request.pilatesTypeId())
                .orElseThrow(()-> new RuntimeException("Pilates type not found with ID: " + request.pilatesTypeId()));

        if (!request.startTime().isBefore(request.endTime())){
            throw new RuntimeException("Start time must be before the end time");
        }

        LocalDateTime classStartDateTime= LocalDateTime.of(request.date(), request.startTime());
        if (classStartDateTime.isBefore(LocalDateTime.now())){
            throw new RuntimeException("Class date and time must be in the future");
        }

        boolean hasOverlap = classSessionRepository.existsByTeacherIdAndDateAndStartTimeLessThanAndEndTimeGreaterThan(
                request.teacherId(),
                request.date(),
                request.endTime(),
                request.startTime()
        );

        if (hasOverlap) {
            throw new RuntimeException("Teacher already has a class scheduled during this time");
        }

        ClassSession newClass = new ClassSession();
        newClass.setDate(request.date());
        newClass.setStartTime(request.startTime());
        newClass.setEndTime(request.endTime());
        newClass.setMachines(request.machines());
        newClass.setStatus(ClassStatus.SCHEDULED);
        newClass.setTeacher(teacher);
        newClass.setPilatesType(pilatesType);

        ClassSession classSaved = classSessionRepository.save(newClass);

        return mapToClass(classSaved);
    }

    @GetMapping
    public List<ClassSessionResponseDTO> getAllClasses(){
        return classSessionRepository.findAll()
                .stream()
                .map(this::mapToClass)
                .toList();
    }

    public void deleteClass(Long id) {
        classSessionRepository.deleteById(id);
    }

    public ClassSessionResponseDTO mapToClass(ClassSession classSession){
        return new ClassSessionResponseDTO(
                classSession.getId(),
                classSession.getDate(),
                classSession.getStartTime(),
                classSession.getEndTime(),
                classSession.getMachines(),
                classSession.getStatus()
        );
    }
}