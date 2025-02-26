package com.example.trainingdiary.controller;

import com.example.trainingdiary.entity.Training;
import com.example.trainingdiary.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainings")
public class TrainingController {
    @Autowired
    private TrainingService trainingService;

    @GetMapping
    public List<Training> getAllTrainings() {
        return trainingService.getAllTrainings();
    }

    @GetMapping("/{id}")
    public Training getTrainingById(@PathVariable Long id) {
        return trainingService.getTrainingById(id);
    }

    @PostMapping
    public Training addTraining(@RequestBody Training training) {
        return trainingService.addTraining(training);
    }

    @DeleteMapping("/{id}")
    public void deleteTraining(@PathVariable Long id) {
        trainingService.deleteTraining(id);
    }
}