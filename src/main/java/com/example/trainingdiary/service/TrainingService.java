package com.example.trainingdiary.service;

import com.example.trainingdiary.dto.TrainingRequestDTO;
import com.example.trainingdiary.dto.TrainingResponseDTO;
import com.example.trainingdiary.entity.Training;
import com.example.trainingdiary.entity.User;
import com.example.trainingdiary.entity.Exercise;
import com.example.trainingdiary.repository.TrainingRepository;
import com.example.trainingdiary.repository.UserRepository;
import com.example.trainingdiary.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    // Отримати всі тренування
    public List<TrainingResponseDTO> getAllTrainings() {
        return trainingRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Отримати тренування за ID
    public TrainingResponseDTO getTrainingById(Long id) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training not found with id " + id));
        return convertToDTO(training);
    }

    // Створити нове тренування
    public TrainingResponseDTO createTraining(TrainingRequestDTO trainingRequestDTO) {
        Long userId = getCurrentUserId(); // Отримати ID поточного користувача
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        Exercise exercise = exerciseRepository.findById(trainingRequestDTO.getExerciseId())
                .orElseThrow(() -> new RuntimeException("Exercise not found with id " + trainingRequestDTO.getExerciseId()));

        Training training = new Training();
        training.setUser(user);
        training.setExercise(exercise);
        training.setDate(trainingRequestDTO.getDate());

        Training savedTraining = trainingRepository.save(training);
        return convertToDTO(savedTraining);
    }

    // Оновити тренування
    public TrainingResponseDTO updateTraining(Long id, TrainingRequestDTO trainingRequestDTO) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training not found with id " + id));

        Long userId = getCurrentUserId(); // Отримати ID поточного користувача
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        Exercise exercise = exerciseRepository.findById(trainingRequestDTO.getExerciseId())
                .orElseThrow(() -> new RuntimeException("Exercise not found with id " + trainingRequestDTO.getExerciseId()));

        training.setUser(user);
        training.setExercise(exercise);
        training.setDate(trainingRequestDTO.getDate());

        Training updatedTraining = trainingRepository.save(training);
        return convertToDTO(updatedTraining);
    }

    // Видалити тренування
    public void deleteTraining(Long id) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training not found with id " + id));
        trainingRepository.delete(training);
    }

    // Отримати ID поточного користувача з токену
    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Отримати ім'я користувача з токену
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username " + username));
        return user.getId();
    }

    // Конвертація сутності в DTO
    private TrainingResponseDTO convertToDTO(Training training) {
        TrainingResponseDTO dto = new TrainingResponseDTO();
        dto.setId(training.getId());
        dto.setUserId(training.getUser().getId());
        dto.setExerciseId(training.getExercise().getId());
        dto.setDate(training.getDate());
        return dto;
    }
}