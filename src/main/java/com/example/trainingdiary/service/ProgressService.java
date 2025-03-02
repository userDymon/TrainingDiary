package com.example.trainingdiary.service;

import com.example.trainingdiary.dto.ProgressRequestDTO;
import com.example.trainingdiary.dto.ProgressResponseDTO;
import com.example.trainingdiary.entity.Progress;
import com.example.trainingdiary.entity.User;
import com.example.trainingdiary.entity.Exercise;
import com.example.trainingdiary.repository.ProgressRepository;
import com.example.trainingdiary.repository.UserRepository;
import com.example.trainingdiary.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgressService {

    @Autowired
    private ProgressRepository progressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    // Отримати ID поточного користувача з токену
    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Отримати ім'я користувача з токену
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username " + username));
        return user.getId();
    }

    // Отримати весь прогрес
    public List<ProgressResponseDTO> getAllProgress() {
        return progressRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Отримати прогрес за ID
    public ProgressResponseDTO getProgressById(Long id) {
        Progress progress = progressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Progress not found with id " + id));
        return convertToDTO(progress);
    }

    // Створити новий прогрес
    public ProgressResponseDTO createProgress(ProgressRequestDTO progressRequestDTO) {
        Long userId = getCurrentUserId(); // Отримати ID поточного користувача
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        Exercise exercise = exerciseRepository.findById(progressRequestDTO.getExerciseId())
                .orElseThrow(() -> new RuntimeException("Exercise not found with id " + progressRequestDTO.getExerciseId()));

        Progress progress = new Progress();
        progress.setUser(user);
        progress.setExercise(exercise);
        progress.setValue(progressRequestDTO.getValue());
        progress.setDate(progressRequestDTO.getDate());

        Progress savedProgress = progressRepository.save(progress);
        return convertToDTO(savedProgress);
    }

    // Оновити прогрес
    public ProgressResponseDTO updateProgress(Long id, ProgressRequestDTO progressRequestDTO) {
        Progress progress = progressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Progress not found with id " + id));

        Long userId = getCurrentUserId(); // Отримати ID поточного користувача
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        Exercise exercise = exerciseRepository.findById(progressRequestDTO.getExerciseId())
                .orElseThrow(() -> new RuntimeException("Exercise not found with id " + progressRequestDTO.getExerciseId()));

        progress.setUser(user);
        progress.setExercise(exercise);
        progress.setValue(progressRequestDTO.getValue());
        progress.setDate(progressRequestDTO.getDate());

        Progress updatedProgress = progressRepository.save(progress);
        return convertToDTO(updatedProgress);
    }

    // Видалити прогрес
    public void deleteProgress(Long id) {
        Progress progress = progressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Progress not found with id " + id));
        progressRepository.delete(progress);
    }

    // Конвертація сутності в DTO
    private ProgressResponseDTO convertToDTO(Progress progress) {
        ProgressResponseDTO dto = new ProgressResponseDTO();
        dto.setId(progress.getId());
        dto.setUserId(progress.getUser().getId());
        dto.setExerciseId(progress.getExercise().getId());
        dto.setValue(progress.getValue());
        dto.setDate(progress.getDate());
        return dto;
    }
}