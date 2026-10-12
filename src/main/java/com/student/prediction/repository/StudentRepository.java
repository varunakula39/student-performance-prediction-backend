package com.student.prediction.repository;

import com.student.prediction.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByRollNo(String rollNo);
    boolean existsByRollNo(String rollNo);
    boolean existsByEmail(String email);

    @Query("SELECT s FROM Student s WHERE " +
           "LOWER(s.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(s.rollNo) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(s.department) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Student> searchStudents(String query);

    @Query("SELECT AVG(s.attendance) FROM Student s")
    Double findAverageAttendance();

    @Query("SELECT AVG(s.internalMarks) FROM Student s")
    Double findAverageInternalMarks();

    @Query("SELECT AVG(s.previousMarks) FROM Student s")
    Double findAveragePreviousMarks();

    @Query("SELECT AVG(s.studyHours) FROM Student s")
    Double findAverageStudyHours();
}
