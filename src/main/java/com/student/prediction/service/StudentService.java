package com.student.prediction.service;

import com.student.prediction.dto.StudentDTO;
import com.student.prediction.entity.Student;
import com.student.prediction.exception.ResourceNotFoundException;
import com.student.prediction.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDTO> getAllStudents(String query) {
        List<Student> students;
        if (query != null && !query.trim().isEmpty()) {
            students = studentRepository.searchStudents(query.trim());
        } else {
            students = studentRepository.findAll();
        }
        return students.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + id));
        return toDTO(student);
    }

    @Transactional
    public StudentDTO createStudent(StudentDTO dto) {
        if (studentRepository.existsByRollNo(dto.getRollNo())) {
            throw new IllegalArgumentException("Roll number already exists: " + dto.getRollNo());
        }
        if (studentRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + dto.getEmail());
        }

        Student student = new Student(
                dto.getRollNo(),
                dto.getName(),
                dto.getEmail(),
                dto.getDepartment(),
                dto.getSemester(),
                dto.getAttendance(),
                dto.getStudyHours(),
                dto.getInternalMarks(),
                dto.getPreviousMarks()
        );

        Student saved = studentRepository.save(student);
        return toDTO(saved);
    }

    @Transactional
    public StudentDTO updateStudent(Long id, StudentDTO dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + id));

        if (!student.getRollNo().equalsIgnoreCase(dto.getRollNo()) && studentRepository.existsByRollNo(dto.getRollNo())) {
            throw new IllegalArgumentException("Roll number already in use: " + dto.getRollNo());
        }

        if (!student.getEmail().equalsIgnoreCase(dto.getEmail()) && studentRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already in use: " + dto.getEmail());
        }

        student.setRollNo(dto.getRollNo());
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setDepartment(dto.getDepartment());
        student.setSemester(dto.getSemester());
        student.setAttendance(dto.getAttendance());
        student.setStudyHours(dto.getStudyHours());
        student.setInternalMarks(dto.getInternalMarks());
        student.setPreviousMarks(dto.getPreviousMarks());

        Student updated = studentRepository.save(student);
        return toDTO(updated);
    }

    @Transactional
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
    }

    private StudentDTO toDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setRollNo(student.getRollNo());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setDepartment(student.getDepartment());
        dto.setSemester(student.getSemester());
        dto.setAttendance(student.getAttendance());
        dto.setStudyHours(student.getStudyHours());
        dto.setInternalMarks(student.getInternalMarks());
        dto.setPreviousMarks(student.getPreviousMarks());
        dto.setCreatedAt(student.getCreatedAt());
        dto.setUpdatedAt(student.getUpdatedAt());
        return dto;
    }
}
