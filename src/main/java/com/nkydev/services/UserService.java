package com.nkydev.services;

import com.nkydev.UserRequestDTO;
import com.nkydev.UserResponseDTO;
import com.nkydev.entities.User;
import com.nkydev.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO createUser(UserRequestDTO request) {
        User user = new User();
        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setPhone(request.phone());
        user.setRole(request.role());

        User savedUser = userRepository.save(user);

        return mapToResponseDTO(savedUser);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("user not found with ID: " + id));
        return mapToResponseDTO(user);
    }

    @Transactional
    public UserResponseDTO updateUser(Long id, UserRequestDTO request) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("user not found with ID: " + id));

        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setPhone(request.phone());
        user.setRole(request.role());

        return mapToResponseDTO(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("user not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    private UserResponseDTO mapToResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole(),
                user.getCreatedAt()
        );
    }
}