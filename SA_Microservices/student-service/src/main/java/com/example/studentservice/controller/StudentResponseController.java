package com.example.studentservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.studentservice.entity.Question;
import com.example.studentservice.entity.Student;
import com.example.studentservice.entity.StudentResponse;
import com.example.studentservice.service.StudentResponseService;
import com.example.studentservice.service.StudentService;

@RestController
@RequestMapping("/responses")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StudentResponseController {
    @Autowired private StudentResponseService studentResponseService;
    @Autowired private StudentService studentService;
    @Autowired private WebClient webClient;
    @PostMapping("/{studentId}/{questionId}")
    public StudentResponse saveStudentResponse(@PathVariable(value="studentId") Integer studentId,@PathVariable(value="questionId") Integer questionId,@RequestBody StudentResponse studentResponse){
        Student s = studentService.findStudentById(studentId);
        Question q = webClient.get().uri("http://localhost:8082/questions/"+questionId).retrieve().bodyToMono(Question.class).block();//syncronous
        
        if(q != null){
            studentResponse.setQuestion(q);
        }
        else{
            
            throw new IllegalArgumentException("Cannot retrive question");
        }
        if(s != null){
            studentResponse.setStudent(s);
        }
        else 
         throw new IllegalArgumentException("Cannot retrive question");
         
            return studentResponseService.saveStudentResponse(studentResponse);
    }
    @GetMapping("/{studentId}")
    public ResponseEntity<List<StudentResponse>>getAllResponsesByStudentId(@PathVariable(value = "studentId") Integer studentId){
        List<StudentResponse> s = new ArrayList<>();
        s = studentResponseService.findByStudentid(studentId);
        return new ResponseEntity<List<StudentResponse>>(s,null, HttpStatus.SC_OK);
    }
    @GetMapping("/list")
    public ResponseEntity<List<StudentResponse>>getAllResponses(){
        List<StudentResponse> s = new ArrayList<>();
        s = studentResponseService.findAll();
        return new ResponseEntity<List<StudentResponse>>(s,null, HttpStatus.SC_OK);
    }
    @PutMapping("/update/{Id}")
    public StudentResponse updateStudentResponse(@PathVariable(value="Id") Integer Id, @RequestBody StudentResponse studentResponse){
     return studentResponseService.updateStudentResponse(studentResponse, Id);
    }
     

    
}
