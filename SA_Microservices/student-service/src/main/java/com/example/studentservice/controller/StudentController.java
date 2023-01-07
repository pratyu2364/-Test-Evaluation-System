package com.example.studentservice.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentservice.entity.Student;
import com.example.studentservice.service.StudentService;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StudentController {
    @Autowired private StudentService studentService;
    @PostMapping("/")
    public Student saveStudent(@RequestBody Student student){
        return studentService.saveStudent(student);  
    }
    @GetMapping("/{id}")
    public Student fetchStudentById(@PathVariable("id") Integer id){
        return studentService.findStudentById(id);
    }
    @PutMapping("/update/{Id}")
    public Student updateStudent(@PathVariable(value="Id") Integer Id, @RequestBody Student student){
     return studentService.updateStudent(student, Id);
    }

    @GetMapping("/list")
    public List<Student> fetchllStudent(){
        return studentService.findAll();
    }


    
}
