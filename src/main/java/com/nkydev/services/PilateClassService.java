package com.nkydev.services;

import com.nkydev.repositories.PilateClassRepository;
import org.springframework.stereotype.Service;

@Service
public class PilateClassService {
    final private PilateClassRepository pilateClassRepository;

    public PilateClassService(PilateClassRepository pilateClassRepository) {
        this.pilateClassRepository = pilateClassRepository;
    }
}
