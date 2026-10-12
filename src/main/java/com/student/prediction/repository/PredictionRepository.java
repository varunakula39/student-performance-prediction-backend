package com.student.prediction.repository;

import com.student.prediction.entity.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredictionRepository extends JpaRepository<Prediction, Long> {
    List<Prediction> findAllByOrderByPredictedAtDesc();
    List<Prediction> findByStudentIdOrderByPredictedAtDesc(Long studentId);

    @Query("SELECT p.performanceCategory, COUNT(p) FROM Prediction p GROUP BY p.performanceCategory")
    List<Object[]> countByPerformanceCategory();

    @Query("SELECT AVG(p.predictedMarks) FROM Prediction p")
    Double findAveragePredictedMarks();
}
