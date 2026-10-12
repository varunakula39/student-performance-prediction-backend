package com.student.prediction.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.prediction.dto.PredictionRequest;
import com.student.prediction.dto.PredictionResponse;
import com.student.prediction.entity.Prediction;
import com.student.prediction.entity.Student;
import com.student.prediction.repository.PredictionRepository;
import com.student.prediction.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.*;

@Service
public class PredictionService {

    private final PredictionRepository predictionRepository;
    private final StudentRepository studentRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${ml.service.url:http://localhost:8001/predict}")
    private String mlServiceUrl;

    public PredictionService(PredictionRepository predictionRepository,
                             StudentRepository studentRepository,
                             RestTemplateBuilder restTemplateBuilder,
                             ObjectMapper objectMapper) {
        this.predictionRepository = predictionRepository;
        this.studentRepository = studentRepository;
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(3))
                .setReadTimeout(Duration.ofSeconds(5))
                .build();
        this.objectMapper = objectMapper;
    }

    @Transactional
    public PredictionResponse predictAndSave(PredictionRequest request) {
        String studentName = request.getStudentName();
        if ((studentName == null || studentName.trim().isEmpty()) && request.getStudentId() != null) {
            Optional<Student> studentOpt = studentRepository.findById(request.getStudentId());
            if (studentOpt.isPresent()) {
                studentName = studentOpt.get().getName();
            }
        }
        if (studentName == null || studentName.trim().isEmpty()) {
            studentName = "Guest Student";
        }

        double attendance = request.getAttendance();
        double studyHours = request.getStudyHours();
        double internalMarks = request.getInternalMarks();
        double previousMarks = request.getPreviousMarks();

        double predictedScore;
        String category;
        List<String> recommendations = new ArrayList<>();

        // 1. Attempt calling Python FastAPI ML microservice
        try {
            Map<String, Object> payload = new HashMap<>();
            payload.put("attendance", attendance);
            payload.put("study_hours", studyHours);
            payload.put("internal_marks", internalMarks);
            payload.put("previous_exam_marks", previousMarks);
            payload.put("student_name", studentName);
            payload.put("student_id", request.getStudentId());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(mlServiceUrl, entity, Map.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Map body = response.getBody();
                predictedScore = ((Number) body.get("predicted_marks")).doubleValue();
                category = (String) body.get("performance_category");
                List<?> recs = (List<?>) body.get("recommendations");
                if (recs != null) {
                    for (Object r : recs) {
                        recommendations.add(String.valueOf(r));
                    }
                }
            } else {
                throw new RuntimeException("Unsuccessful ML Service response");
            }
        } catch (Exception e) {
            System.err.println("[PredictionService] Notice: Could not reach Python ML service at " + mlServiceUrl
                    + " (" + e.getMessage() + "). Applying calibrated regression fallback.");

            // Calibrated regression fallback identical to ML model
            predictedScore = 0.15 * attendance + 2.20 * Math.min(studyHours, 10.0)
                    + 0.35 * internalMarks + 0.40 * previousMarks;
            predictedScore = Math.max(0.0, Math.min(100.0, Math.round(predictedScore * 10.0) / 10.0));
            category = categorize(predictedScore);
            recommendations = generateFallbackRecommendations(attendance, studyHours, internalMarks, predictedScore);
        }

        // 2. Persist prediction in MySQL database
        String recsJson = "[]";
        try {
            recsJson = objectMapper.writeValueAsString(recommendations);
        } catch (Exception ignored) {
        }

        Prediction entity = new Prediction(
                request.getStudentId(),
                studentName,
                attendance,
                studyHours,
                internalMarks,
                previousMarks,
                predictedScore,
                category,
                recsJson
        );

        Prediction saved = predictionRepository.save(entity);

        // 3. Return DTO response
        return toResponse(saved);
    }

    public List<PredictionResponse> getAllPredictions() {
        List<Prediction> list = predictionRepository.findAllByOrderByPredictedAtDesc();
        List<PredictionResponse> responses = new ArrayList<>();
        for (Prediction p : list) {
            responses.add(toResponse(p));
        }
        return responses;
    }

    public List<PredictionResponse> getPredictionsForStudent(Long studentId) {
        List<Prediction> list = predictionRepository.findByStudentIdOrderByPredictedAtDesc(studentId);
        List<PredictionResponse> responses = new ArrayList<>();
        for (Prediction p : list) {
            responses.add(toResponse(p));
        }
        return responses;
    }

    private String categorize(double score) {
        if (score >= 80.0) return "Excellent";
        if (score >= 65.0) return "Good";
        if (score >= 50.0) return "Average";
        return "Needs Improvement";
    }

    private List<String> generateFallbackRecommendations(double attendance, double studyHours, double internalMarks, double predicted) {
        List<String> list = new ArrayList<>();
        if (studyHours < 2.5) {
            list.add("Increase daily dedicated study time to at least 3 to 4 hours.");
        } else {
            list.add("Maintain consistent daily revision sessions.");
        }
        if (attendance < 75.0) {
            list.add("Attendance is below the mandatory 75% threshold. Attend regular lectures to avoid exam disqualification.");
        } else {
            list.add("Great attendance! Continue participating actively in classroom discussions.");
        }
        if (internalMarks < 60.0) {
            list.add("Seek faculty guidance and submit practice assignments to boost internal assessment score.");
        }
        if (predicted >= 80.0) {
            list.add("Outstanding predicted grade! Target distinctions and competitive project opportunities.");
        } else if (predicted < 50.0) {
            list.add("Immediate remedial tutorial sessions and structured study schedule are highly recommended.");
        }
        return list;
    }

    private PredictionResponse toResponse(Prediction p) {
        PredictionResponse res = new PredictionResponse();
        res.setId(p.getId());
        res.setStudentId(p.getStudentId());
        res.setStudentName(p.getStudentName());
        res.setAttendance(p.getAttendance());
        res.setStudyHours(p.getStudyHours());
        res.setInternalMarks(p.getInternalMarks());
        res.setPreviousMarks(p.getPreviousMarks());
        res.setPredictedMarks(p.getPredictedMarks());
        res.setPerformanceCategory(p.getPerformanceCategory());
        res.setPredictedAt(p.getPredictedAt());

        List<String> recList = new ArrayList<>();
        if (p.getRecommendations() != null && !p.getRecommendations().trim().isEmpty()) {
            try {
                recList = objectMapper.readValue(p.getRecommendations(), new TypeReference<List<String>>() {});
            } catch (Exception e) {
                recList.add(p.getRecommendations());
            }
        }
        res.setRecommendations(recList);
        return res;
    }
}
