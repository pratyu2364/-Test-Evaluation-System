package com.example.studentservice.service;

import java.util.List;

import com.example.studentservice.entity.Student;

public interface StudentService {
    Student saveStudent(Student student);
    void deleteStudentById(Integer studentId);
    Student findStudentById(Integer id);
    Student updateStudent(Student student,Integer id);
    public List<Student>findAll();
    
}
