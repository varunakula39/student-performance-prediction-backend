package com.student.prediction.controller;

import com.student.prediction.dto.PredictionRequest;
import com.student.prediction.dto.PredictionResponse;
import com.student.prediction.service.PredictionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predictions")
public class PredictionController {

    private final PredictionService predictionService;

    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @PostMapping("/predict")
    public ResponseEntity<PredictionResponse> predict(@Valid @RequestBody PredictionRequest request) {
        PredictionResponse response = predictionService.predictAndSave(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PredictionResponse>> getAllPredictions() {
        List<PredictionResponse> list = predictionService.getAllPredictions();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<PredictionResponse>> getPredictionsForStudent(@PathVariable Long studentId) {
        List<PredictionResponse> list = predictionService.getPredictionsForStudent(studentId);
        return ResponseEntity.ok(list);
    }
}
