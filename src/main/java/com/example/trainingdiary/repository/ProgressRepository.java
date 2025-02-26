package com.example.trainingdiary.repository;

import com.example.trainingdiary.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
}
