package com.example.trainingdiary.controller;

import com.example.trainingdiary.dto.ProgressRequestDTO;
import com.example.trainingdiary.dto.ProgressResponseDTO;
import com.example.trainingdiary.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    @Autowired
    private ProgressService progressService;

    // Отримати весь прогрес (вимагає токену)
    @GetMapping
    public List<ProgressResponseDTO> getAllProgress() {
        return progressService.getAllProgress();
    }

    // Отримати прогрес за ID (вимагає токену)
    @GetMapping("/{id}")
    public ResponseEntity<ProgressResponseDTO> getProgressById(@PathVariable Long id) {
        return ResponseEntity.ok(progressService.getProgressById(id));
    }

    // Створити новий прогрес (вимагає токену)
    @PostMapping
    public ResponseEntity<ProgressResponseDTO> createProgress(@RequestBody ProgressRequestDTO progressRequestDTO) {
        return ResponseEntity.ok(progressService.createProgress(progressRequestDTO));
    }

    // Оновити прогрес (вимагає токену)
    @PutMapping("/{id}")
    public ResponseEntity<ProgressResponseDTO> updateProgress(
            @PathVariable Long id, @RequestBody ProgressRequestDTO progressRequestDTO) {
        return ResponseEntity.ok(progressService.updateProgress(id, progressRequestDTO));
    }

    // Видалити прогрес (вимагає токену)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgress(@PathVariable Long id) {
        progressService.deleteProgress(id);
        return ResponseEntity.noContent().build();
    }
}