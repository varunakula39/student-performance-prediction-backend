package com.student.prediction.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class PredictionRequest {

    private Long studentId;
    private String studentName;

    @NotNull(message = "Attendance is required")
    @DecimalMin(value = "0.0", message = "Attendance must be >= 0")
    @DecimalMax(value = "100.0", message = "Attendance must be <= 100")
    private Double attendance;

    @NotNull(message = "Study hours is required")
    @DecimalMin(value = "0.0", message = "Study hours must be >= 0")
    @DecimalMax(value = "24.0", message = "Study hours must be <= 24")
    private Double studyHours;

    @NotNull(message = "Internal marks is required")
    @DecimalMin(value = "0.0", message = "Internal marks must be >= 0")
    @DecimalMax(value = "100.0", message = "Internal marks must be <= 100")
    private Double internalMarks;

    @NotNull(message = "Previous marks is required")
    @DecimalMin(value = "0.0", message = "Previous marks must be >= 0")
    @DecimalMax(value = "100.0", message = "Previous marks must be <= 100")
    private Double previousMarks;

    public PredictionRequest() {
    }

    public PredictionRequest(Long studentId, String studentName, Double attendance, Double studyHours,
                             Double internalMarks, Double previousMarks) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.attendance = attendance;
        this.studyHours = studyHours;
        this.internalMarks = internalMarks;
        this.previousMarks = previousMarks;
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
}
