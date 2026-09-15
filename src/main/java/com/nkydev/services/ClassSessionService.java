package com.nkydev.services;

import com.nkydev.repositories.ClassSessionRepository;
import org.springframework.stereotype.Service;

@Service
public class ClassSessionService {
    private final ClassSessionRepository classSessionRepository;

    public ClassSessionService(ClassSessionRepository classSessionRepository) {
        this.classSessionRepository = classSessionRepository;
    }
}
