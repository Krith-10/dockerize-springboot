package com.demo.student.controller;

import com.demo.student.entity.Student;
import com.demo.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> studentList = studentService.getStudents();
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<String> createStudent(@RequestBody Student s) {
        Student student = studentService.createStudent(s);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student created successfully with the id "+ s.getId());
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable("studentId") int studentId) {
        Student s = studentService.getStudentById(studentId);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @PatchMapping("/students/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable("studentId") int studentId, @RequestBody Student student) {
        Student s = studentService.updateStudent(studentId, student);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable("studentId") int studentId) {
        studentService.deleteStudent(studentId);
        return new ResponseEntity("Student deleted successfully", HttpStatus.NO_CONTENT);
    }

}
