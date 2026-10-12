package com.student.prediction.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "predictions")
public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "student_name", nullable = false, length = 100)
    private String studentName;

    @Column(nullable = false)
    private Double attendance;

    @Column(name = "study_hours", nullable = false)
    private Double studyHours;

    @Column(name = "internal_marks", nullable = false)
    private Double internalMarks;

    @Column(name = "previous_marks", nullable = false)
    private Double previousMarks;

    @Column(name = "predicted_marks", nullable = false)
    private Double predictedMarks;

    @Column(name = "performance_category", nullable = false, length = 50)
    private String performanceCategory;

    @Column(columnDefinition = "TEXT")
    private String recommendations;

    @Column(name = "predicted_at")
    private LocalDateTime predictedAt = LocalDateTime.now();

    public Prediction() {
    }

    public Prediction(Long studentId, String studentName, Double attendance, Double studyHours,
                      Double internalMarks, Double previousMarks, Double predictedMarks,
                      String performanceCategory, String recommendations) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.attendance = attendance;
        this.studyHours = studyHours;
        this.internalMarks = internalMarks;
        this.previousMarks = previousMarks;
        this.predictedMarks = predictedMarks;
        this.performanceCategory = performanceCategory;
        this.recommendations = recommendations;
        this.predictedAt = LocalDateTime.now();
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

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public LocalDateTime getPredictedAt() {
        return predictedAt;
    }

    public void setPredictedAt(LocalDateTime predictedAt) {
        this.predictedAt = predictedAt;
    }
}
