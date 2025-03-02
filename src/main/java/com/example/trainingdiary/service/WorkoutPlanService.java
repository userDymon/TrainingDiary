package com.example.trainingdiary.service;

import com.example.trainingdiary.dto.WorkoutPlanRequestDTO;
import com.example.trainingdiary.dto.WorkoutPlanResponseDTO;
import com.example.trainingdiary.entity.WorkoutPlan;
import com.example.trainingdiary.entity.User;
import com.example.trainingdiary.entity.Exercise;
import com.example.trainingdiary.repository.WorkoutPlanRepository;
import com.example.trainingdiary.repository.UserRepository;
import com.example.trainingdiary.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutPlanService {

    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;

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

    // Отримати всі плани тренувань
    public List<WorkoutPlanResponseDTO> getAllWorkoutPlans() {
        return workoutPlanRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Отримати план тренувань за ID
    public WorkoutPlanResponseDTO getWorkoutPlanById(Long id) {
        WorkoutPlan workoutPlan = workoutPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WorkoutPlan not found with id " + id));
        return convertToDTO(workoutPlan);
    }

    // Створити новий план тренувань
    public WorkoutPlanResponseDTO createWorkoutPlan(WorkoutPlanRequestDTO workoutPlanRequestDTO) {
        Long userId = getCurrentUserId(); // Отримати ID поточного користувача
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        List<Exercise> exercises = exerciseRepository.findAllById(workoutPlanRequestDTO.getExerciseIds());

        WorkoutPlan workoutPlan = new WorkoutPlan();
        workoutPlan.setName(workoutPlanRequestDTO.getName());
        workoutPlan.setDescription(workoutPlanRequestDTO.getDescription());
        workoutPlan.setUser(user);
        workoutPlan.setExercises(exercises);

        WorkoutPlan savedWorkoutPlan = workoutPlanRepository.save(workoutPlan);
        return convertToDTO(savedWorkoutPlan);
    }

    // Оновити план тренувань
    public WorkoutPlanResponseDTO updateWorkoutPlan(Long id, WorkoutPlanRequestDTO workoutPlanRequestDTO) {
        WorkoutPlan workoutPlan = workoutPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WorkoutPlan not found with id " + id));

        Long userId = getCurrentUserId(); // Отримати ID поточного користувача
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        List<Exercise> exercises = exerciseRepository.findAllById(workoutPlanRequestDTO.getExerciseIds());

        workoutPlan.setName(workoutPlanRequestDTO.getName());
        workoutPlan.setDescription(workoutPlanRequestDTO.getDescription());
        workoutPlan.setUser(user);
        workoutPlan.setExercises(exercises);

        WorkoutPlan updatedWorkoutPlan = workoutPlanRepository.save(workoutPlan);
        return convertToDTO(updatedWorkoutPlan);
    }

    // Видалити план тренувань
    public void deleteWorkoutPlan(Long id) {
        WorkoutPlan workoutPlan = workoutPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WorkoutPlan not found with id " + id));
        workoutPlanRepository.delete(workoutPlan);
    }

    // Конвертація сутності в DTO
    private WorkoutPlanResponseDTO convertToDTO(WorkoutPlan workoutPlan) {
        WorkoutPlanResponseDTO dto = new WorkoutPlanResponseDTO();
        dto.setId(workoutPlan.getId());
        dto.setName(workoutPlan.getName());
        dto.setDescription(workoutPlan.getDescription());
        dto.setUserId(workoutPlan.getUser().getId());
        dto.setExerciseIds(workoutPlan.getExercises().stream()
                .map(Exercise::getId)
                .collect(Collectors.toList()));
        return dto;
    }
}
