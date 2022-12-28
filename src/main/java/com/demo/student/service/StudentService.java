package com.demo.student.service;

import com.demo.student.entity.Student;
import com.demo.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudents() {
        List <Student> students = studentRepository.findAll();
        return students;
    }

    public Student createStudent(Student s) {
        Student student = studentRepository.save(s);
        return student;
    }

    public Student getStudentById(int id) {
        Optional<Student> s = studentRepository.findById(id);
        if(!s.isPresent())
            throw new RuntimeException();
        return s.get();
    }

    public Student updateStudent(int studentId, Student student) {
        Optional<Student> studentToBeUpdated = studentRepository.findById(studentId);
        if(studentToBeUpdated.isPresent()) {
            if(student.getName() != null && !student.getName().equals(studentToBeUpdated.get().getName()))
                studentToBeUpdated.get().setName(student.getName());
            if( Objects.nonNull(student.getGrade()) && student.getGrade() != studentToBeUpdated.get().getGrade())
                studentToBeUpdated.get().setGrade(student.getGrade());
            if(Objects.nonNull(student.getPlace()) && !student.getPlace().equals(studentToBeUpdated.get().getPlace()))
                studentToBeUpdated.get().setPlace(student.getPlace());
        }
        studentRepository.save(studentToBeUpdated.get());
        return studentToBeUpdated.get();
    }

    public void deleteStudent(int studentId) {
        studentRepository.deleteById(studentId);
    }

}
