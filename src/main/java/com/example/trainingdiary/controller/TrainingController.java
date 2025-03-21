package com.example.trainingdiary.controller;

import com.example.trainingdiary.dto.TrainingRequestDTO;
import com.example.trainingdiary.dto.TrainingResponseDTO;
import com.example.trainingdiary.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainings")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    // Отримати всі тренування (вимагає токену)
    @GetMapping
    public List<TrainingResponseDTO> getAllTrainings() {
        return trainingService.getAllTrainings();
    }

    // Отримати тренування за ID (вимагає токену)
    @GetMapping("/{id}")
    public ResponseEntity<TrainingResponseDTO> getTrainingById(@PathVariable Long id) {
        return ResponseEntity.ok(trainingService.getTrainingById(id));
    }

    // Створити нове тренування (вимагає токену)
    @PostMapping
    public ResponseEntity<TrainingResponseDTO> createTraining(@RequestBody TrainingRequestDTO trainingRequestDTO) {
        return ResponseEntity.ok(trainingService.createTraining(trainingRequestDTO));
    }

    // Оновити тренування (вимагає токену)
    @PutMapping("/{id}")
    public ResponseEntity<TrainingResponseDTO> updateTraining(
            @PathVariable Long id, @RequestBody TrainingRequestDTO trainingRequestDTO) {
        return ResponseEntity.ok(trainingService.updateTraining(id, trainingRequestDTO));
    }

    // Видалити тренування (вимагає токену)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTraining(@PathVariable Long id) {
        trainingService.deleteTraining(id);
        return ResponseEntity.noContent().build();
    }
}