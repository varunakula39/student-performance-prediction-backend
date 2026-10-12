package com.student.prediction.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class StudentDTO {

    private Long id;

    @NotBlank(message = "Roll number is required")
    private String rollNo;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Valid email is required")
    private String email;

    @NotBlank(message = "Department is required")
    private String department;

    @NotNull(message = "Semester is required")
    @Min(value = 1, message = "Semester must be at least 1")
    @Max(value = 8, message = "Semester must be at most 8")
    private Integer semester;

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

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public StudentDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
