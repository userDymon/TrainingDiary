package com.example.trainingdiary.service;

import com.example.trainingdiary.entity.Training;
import com.example.trainingdiary.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {
    @Autowired
    private TrainingRepository trainingRepository;

    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    public Training getTrainingById(Long id) {
        return trainingRepository.findById(id).orElse(null);
    }

    public Training addTraining(Training training) {
        return trainingRepository.save(training);
    }

    public void deleteTraining(Long id) {
        trainingRepository.deleteById(id);
    }
}