package com.student.prediction.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PredictionResponse {

    private Long id;
    private Long studentId;
    private String studentName;
    private Double attendance;
    private Double studyHours;
    private Double internalMarks;
    private Double previousMarks;
    private Double predictedMarks;
    private String performanceCategory;
    private List<String> recommendations;
    private LocalDateTime predictedAt;

    public PredictionResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Double getAttendance() {
        return attendance;
    }

    public void setAttendance(Double attendance) {
        this.attendance = attendance;
    }

    public Double getStudyHours() {
        return studyHours;
    }

    public void setStudyHours(Double studyHours) {
        this.studyHours = studyHours;
    }

    public Double getInternalMarks() {
        return internalMarks;
    }

    public void setInternalMarks(Double internalMarks) {
        this.internalMarks = internalMarks;
    }

    public Double getPreviousMarks() {
        return previousMarks;
    }

    public void setPreviousMarks(Double previousMarks) {
        this.previousMarks = previousMarks;
    }

    public Double getPredictedMarks() {
        return predictedMarks;
    }

    public void setPredictedMarks(Double predictedMarks) {
        this.predictedMarks = predictedMarks;
    }

    public String getPerformanceCategory() {
        return performanceCategory;
    }

    public void setPerformanceCategory(String performanceCategory) {
        this.performanceCategory = performanceCategory;
    }

    public List<String> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<String> recommendations) {
        this.recommendations = recommendations;
    }

    public LocalDateTime getPredictedAt() {
        return predictedAt;
    }

    public void setPredictedAt(LocalDateTime predictedAt) {
        this.predictedAt = predictedAt;
    }
}
