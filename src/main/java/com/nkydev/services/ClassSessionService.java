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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    @Transactional
    public ClassSessionResponseDTO createClass(ClassSessionRequestDTO request){

        User teacher = userRepository.findById(request.teacherId())
                .orElseThrow(()-> new RuntimeException("Teacher not found with ID: " + request.teacherId()));

        if (teacher.getRole() != Role.TEACHER){
            throw new RuntimeException("The user isn't a TEACHER");
        }

        PilatesType pilatesType = pilatesTypeRepository.findById(request.pilatesTypeId())
                .orElseThrow(()-> new RuntimeException("Pilates type not found with ID: " + request.pilatesTypeId()));

        if (!request.startTime().isBefore(request.endTime())){
            throw new RuntimeException("Start time must be before the End Time");
        }

        LocalDateTime classStartDateTime= LocalDateTime.of(request.date(), request.startTime());
        if (classStartDateTime.isBefore(LocalDateTime.now())){
            throw new RuntimeException("Class date and time must be in the future");
        }

        boolean hasOverlap = classSessionRepository.existByTeacherInClassSession(
                request.teacherId(),
                request.date(),
                request.endTime(),
                request.startTime()
        );

        if (hasOverlap) {
            throw new RuntimeException("Teacher already has a class scheduled during this time");
        }

        if (request.machines() < 1 || request.machines() > pilatesType.getMaxCapacity()) {
            throw new RuntimeException("The count of machines must be between 1 and " + pilatesType.getMaxCapacity());
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

    @Transactional(readOnly = true)
    public List<ClassSessionResponseDTO> getAllClasses(){
        return classSessionRepository.findAll()
                .stream()
                .map(this::mapToClass)
                .toList();
    }

    @Transactional(readOnly = true)
    public ClassSessionResponseDTO getClassById(Long id){
        ClassSession classSession = classSessionRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Class not found with ID: " + id));

        return mapToClass(classSession);
    }

    @Transactional(readOnly = true)
    public List<ClassSessionResponseDTO> getAvailableClasses(){
        LocalDate today = LocalDate.now();
        LocalTime now= LocalTime.now();

        return classSessionRepository.findAvailableClass(ClassStatus.SCHEDULED, today, now)
                .stream()
                .map(this::mapToClass)
                .toList();
    }

    @Transactional
    public ClassSessionResponseDTO updateClass(Long id, ClassSessionRequestDTO request) {
        ClassSession classSession = classSessionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found with ID: " + id));

        User teacher = userRepository.findById(request.teacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found with ID: " + request.teacherId()));

        if (teacher.getRole() != Role.TEACHER) {
            throw new RuntimeException("The user isn't a TEACHER");
        }

        PilatesType pilatesType = pilatesTypeRepository.findById(request.pilatesTypeId())
                .orElseThrow(() -> new RuntimeException("Pilates type not found with ID: " + request.pilatesTypeId()));

        if (!request.startTime().isBefore(request.endTime())) {
            throw new RuntimeException("Start time must be before the End Time");
        }

        LocalDateTime classStartDateTime = LocalDateTime.of(request.date(), request.startTime());
        if (classStartDateTime.isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Class date and time must be in the future");
        }

        if (request.machines() < 1 || request.machines() > pilatesType.getMaxCapacity()) {
            throw new RuntimeException("The count of machines must be between 1 and " + pilatesType.getMaxCapacity());
        }

        classSession.setDate(request.date());
        classSession.setStartTime(request.startTime());
        classSession.setEndTime(request.endTime());
        classSession.setMachines(request.machines());
        classSession.setTeacher(teacher);
        classSession.setPilatesType(pilatesType);

        return mapToClass(classSession);
    }

    @Transactional
    public ClassSessionResponseDTO updateClassStatus(Long id, ClassStatus newStatus) {
        ClassSession classSession = classSessionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found with ID: " + id));

        classSession.setStatus(newStatus);

        return mapToClass(classSession);
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