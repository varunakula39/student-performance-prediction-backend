package com.student.prediction.config;

import com.student.prediction.entity.Student;
import com.student.prediction.entity.User;
import com.student.prediction.entity.Prediction;
import com.student.prediction.repository.PredictionRepository;
import com.student.prediction.repository.StudentRepository;
import com.student.prediction.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final PredictionRepository predictionRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           StudentRepository studentRepository,
                           PredictionRepository predictionRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.predictionRepository = predictionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        seedUsers();
        seedStudents();
        seedInitialPredictions();
    }

    private void seedUsers() {
        if (!userRepository.existsByEmail("admin@school.com")) {
            User admin = new User(
                    "admin@school.com",
                    passwordEncoder.encode("admin123"),
                    "System Administrator",
                    "ROLE_ADMIN"
            );
            userRepository.save(admin);
            System.out.println("[DataInitializer] Created default Admin user: admin@school.com / admin123");
        }

        if (!userRepository.existsByEmail("admin@demo.com")) {
            User adminDemo = new User(
                    "admin@demo.com",
                    passwordEncoder.encode("admin123"),
                    "Demo Administrator",
                    "ROLE_ADMIN"
            );
            userRepository.save(adminDemo);
            System.out.println("[DataInitializer] Created default Admin user: admin@demo.com / admin123");
        }

        if (!userRepository.existsByEmail("teacher@school.com")) {
            User teacher = new User(
                    "teacher@school.com",
                    passwordEncoder.encode("teacher123"),
                    "Professor Sarah Johnson",
                    "ROLE_TEACHER"
            );
            userRepository.save(teacher);
            System.out.println("[DataInitializer] Created default Teacher user: teacher@school.com / teacher123");
        }

        if (!userRepository.existsByEmail("teacher@demo.com")) {
            User teacherDemo = new User(
                    "teacher@demo.com",
                    passwordEncoder.encode("teacher123"),
                    "Demo Faculty Member",
                    "ROLE_TEACHER"
            );
            userRepository.save(teacherDemo);
            System.out.println("[DataInitializer] Created default Teacher user: teacher@demo.com / teacher123");
        }
    }

    private void seedStudents() {
        if (studentRepository.count() == 0) {
            List<Student> sampleStudents = Arrays.asList(
                new Student("CS2024-001", "Aarav Sharma", "aarav.sharma@example.edu", "Computer Science", 6, 92.5, 6.5, 88.0, 85.0),
                new Student("CS2024-002", "Diya Patel", "diya.patel@example.edu", "Computer Science", 6, 85.0, 5.0, 78.5, 81.0),
                new Student("CS2024-003", "Rohan Mehta", "rohan.mehta@example.edu", "Computer Science", 6, 62.0, 2.0, 48.0, 52.0),
                new Student("AI2024-004", "Ananya Iyer", "ananya.iyer@example.edu", "Artificial Intelligence", 4, 96.0, 7.5, 94.0, 92.5),
                new Student("AI2024-005", "Vikram Singh", "vikram.singh@example.edu", "Artificial Intelligence", 4, 78.0, 3.5, 65.0, 68.0),
                new Student("AI2024-006", "Sneha Rao", "sneha.rao@example.edu", "Artificial Intelligence", 4, 55.0, 1.5, 42.0, 45.0),
                new Student("IT2024-007", "Kabir Verma", "kabir.verma@example.edu", "Information Technology", 5, 88.0, 5.5, 82.0, 79.0),
                new Student("IT2024-008", "Meera Nair", "meera.nair@example.edu", "Information Technology", 5, 81.5, 4.0, 71.0, 74.0),
                new Student("IT2024-009", "Aditya Joshi", "aditya.joshi@example.edu", "Information Technology", 5, 68.0, 2.5, 54.0, 58.0),
                new Student("EC2024-010", "Pooja Reddy", "pooja.reddy@example.edu", "Electronics & Comm.", 6, 94.0, 6.0, 90.0, 88.0),
                new Student("EC2024-011", "Arjun Kapoor", "arjun.kapoor@example.edu", "Electronics & Comm.", 6, 73.0, 3.0, 60.0, 62.0),
                new Student("EC2024-012", "Kavya Pillai", "kavya.pillai@example.edu", "Electronics & Comm.", 6, 89.0, 5.2, 84.0, 83.5),
                new Student("DS2024-013", "Dev Malhotra", "dev.malhotra@example.edu", "Data Science", 3, 91.0, 6.0, 86.0, 87.0),
                new Student("DS2024-014", "Ishita Sen", "ishita.sen@example.edu", "Data Science", 3, 76.5, 3.8, 67.0, 70.0),
                new Student("DS2024-015", "Rahul Das", "rahul.das@example.edu", "Data Science", 3, 58.0, 1.8, 45.0, 48.0),
                new Student("CS2024-016", "Tanya Bansal", "tanya.bansal@example.edu", "Computer Science", 6, 87.0, 4.5, 79.0, 80.0),
                new Student("AI2024-017", "Kunal Deshmukh", "kunal.d@example.edu", "Artificial Intelligence", 4, 83.0, 4.2, 73.0, 75.0),
                new Student("IT2024-018", "Simran Kaur", "simran.kaur@example.edu", "Information Technology", 5, 95.5, 7.0, 92.0, 91.0)
            );
            studentRepository.saveAll(sampleStudents);
            System.out.println("[DataInitializer] Seeded 18 sample students into database.");
        }
    }

    private void seedInitialPredictions() {
        if (predictionRepository.count() == 0) {
            Prediction p1 = new Prediction(
                    1L,
                    "Aarav Sharma",
                    92.5,
                    6.5,
                    88.0,
                    85.0,
                    88.4,
                    "Excellent",
                    "[\"Great study schedule! Maintain your consistent study routine.\", \"Excellent attendance record. Continue participating actively in classroom discussions.\", \"Solid performance in internal assessments. Keep up the high standard.\", \"Predicted for honors/distinction. Take on advanced projects or competitive exams.\"]"
            );

            Prediction p2 = new Prediction(
                    3L,
                    "Rohan Mehta",
                    62.0,
                    2.0,
                    48.0,
                    52.0,
                    49.2,
                    "Needs Improvement",
                    "[\"Increase daily dedicated study time to at least 3 to 4 hours.\", \"Attendance is currently 62.0% (below the 75% threshold). Attend regular lectures and lab sessions to prevent exam debarment.\", \"Internal marks need urgent attention. Consult professors for clarification on weak topics and submit assignments on time.\", \"Immediate academic counseling and remedial tutorial classes are strongly recommended.\"]"
            );

            predictionRepository.saveAll(Arrays.asList(p1, p2));
            System.out.println("[DataInitializer] Seeded sample predictions into database.");
        }
    }
}
