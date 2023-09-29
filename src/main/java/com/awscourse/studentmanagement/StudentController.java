package com.awscourse.studentmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping("/")
    public Student addStudent(
        @RequestParam("name") String name,
        @RequestParam("regID") String regID,
        @RequestParam("email") String email
    ) {
        // Create a Student object from the form fields
        Student student = new Student();
        student.setName(name);
        student.setRegID(regID);
        student.setEmail(email);
    
        // Save the student to the database
        return studentRepository.save(student);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }
}