package com.student.prediction.dto;

import java.util.Map;

public class DashboardStatsDTO {

    private long totalStudents;
    private double averageMarks;
    private double averageAttendance;
    private double averageStudyHours;
    private long totalPredictions;
    private Map<String, Long> performanceCategories;

    public DashboardStatsDTO() {
    }

    public DashboardStatsDTO(long totalStudents, double averageMarks, double averageAttendance,
                             double averageStudyHours, long totalPredictions,
                             Map<String, Long> performanceCategories) {
        this.totalStudents = totalStudents;
        this.averageMarks = averageMarks;
        this.averageAttendance = averageAttendance;
        this.averageStudyHours = averageStudyHours;
        this.totalPredictions = totalPredictions;
        this.performanceCategories = performanceCategories;
    }

    public long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public double getAverageMarks() {
        return averageMarks;
    }

    public void setAverageMarks(double averageMarks) {
        this.averageMarks = averageMarks;
    }

    public double getAverageAttendance() {
        return averageAttendance;
    }

    public void setAverageAttendance(double averageAttendance) {
        this.averageAttendance = averageAttendance;
    }

    public double getAverageStudyHours() {
        return averageStudyHours;
    }

    public void setAverageStudyHours(double averageStudyHours) {
        this.averageStudyHours = averageStudyHours;
    }

    public long getTotalPredictions() {
        return totalPredictions;
    }

    public void setTotalPredictions(long totalPredictions) {
        this.totalPredictions = totalPredictions;
    }

    public Map<String, Long> getPerformanceCategories() {
        return performanceCategories;
    }

    public void setPerformanceCategories(Map<String, Long> performanceCategories) {
        this.performanceCategories = performanceCategories;
    }
}
