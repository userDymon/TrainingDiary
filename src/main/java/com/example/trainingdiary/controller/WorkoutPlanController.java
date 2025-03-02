package com.example.trainingdiary.controller;

import com.example.trainingdiary.dto.WorkoutPlanRequestDTO;
import com.example.trainingdiary.dto.WorkoutPlanResponseDTO;
import com.example.trainingdiary.service.WorkoutPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout-plans")
public class WorkoutPlanController {

    @Autowired
    private WorkoutPlanService workoutPlanService;

    // Отримати всі плани тренувань
    @GetMapping
    public List<WorkoutPlanResponseDTO> getAllWorkoutPlans() {
        return workoutPlanService.getAllWorkoutPlans();
    }

    // Отримати план тренувань за ID
    @GetMapping("/{id}")
    public ResponseEntity<WorkoutPlanResponseDTO> getWorkoutPlanById(@PathVariable Long id) {
        return ResponseEntity.ok(workoutPlanService.getWorkoutPlanById(id));
    }

    // Створити новий план тренувань
    @PostMapping
    public ResponseEntity<WorkoutPlanResponseDTO> createWorkoutPlan(@RequestBody WorkoutPlanRequestDTO workoutPlanRequestDTO) {
        return ResponseEntity.ok(workoutPlanService.createWorkoutPlan(workoutPlanRequestDTO));
    }

    // Оновити план тренувань
    @PutMapping("/{id}")
    public ResponseEntity<WorkoutPlanResponseDTO> updateWorkoutPlan(
            @PathVariable Long id, @RequestBody WorkoutPlanRequestDTO workoutPlanRequestDTO) {
        return ResponseEntity.ok(workoutPlanService.updateWorkoutPlan(id, workoutPlanRequestDTO));
    }

    // Видалити план тренувань
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutPlan(@PathVariable Long id) {
        workoutPlanService.deleteWorkoutPlan(id);
        return ResponseEntity.noContent().build();
    }
}