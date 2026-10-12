package com.student.prediction.service;

import com.student.prediction.dto.DashboardStatsDTO;
import com.student.prediction.repository.PredictionRepository;
import com.student.prediction.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    private final StudentRepository studentRepository;
    private final PredictionRepository predictionRepository;

    public DashboardService(StudentRepository studentRepository, PredictionRepository predictionRepository) {
        this.studentRepository = studentRepository;
        this.predictionRepository = predictionRepository;
    }

    public DashboardStatsDTO getDashboardStats() {
        long totalStudents = studentRepository.count();
        Double avgAttendance = studentRepository.findAverageAttendance();
        Double avgInternalMarks = studentRepository.findAverageInternalMarks();
        Double avgStudyHours = studentRepository.findAverageStudyHours();
        long totalPredictions = predictionRepository.count();

        Map<String, Long> categoryCounts = new HashMap<>();
        categoryCounts.put("Excellent", 0L);
        categoryCounts.put("Good", 0L);
        categoryCounts.put("Average", 0L);
        categoryCounts.put("Needs Improvement", 0L);

        List<Object[]> rows = predictionRepository.countByPerformanceCategory();
        for (Object[] r : rows) {
            String category = (String) r[0];
            Long count = ((Number) r[1]).longValue();
            categoryCounts.put(category, count);
        }

        return new DashboardStatsDTO(
                totalStudents,
                avgInternalMarks != null ? Math.round(avgInternalMarks * 10.0) / 10.0 : 0.0,
                avgAttendance != null ? Math.round(avgAttendance * 10.0) / 10.0 : 0.0,
                avgStudyHours != null ? Math.round(avgStudyHours * 10.0) / 10.0 : 0.0,
                totalPredictions,
                categoryCounts
        );
    }
}
