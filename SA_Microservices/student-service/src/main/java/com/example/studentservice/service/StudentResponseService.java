package com.example.studentservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.studentservice.entity.StudentResponse;
import com.example.studentservice.repository.StudentResponseRepository;

@Service
public class StudentResponseService {
    @Autowired
    private StudentResponseRepository studentResponseRepository;
    public StudentResponse saveStudentResponse(StudentResponse studentResponse){
        return studentResponseRepository.save(studentResponse);
    }
    public List<StudentResponse> findByStudentid(int sid){
        return studentResponseRepository.findByStudentId(sid);
        
    }
    public List<StudentResponse>findAll(){
        return studentResponseRepository.findAll();
    }
    public StudentResponse updateStudentResponse(StudentResponse studentResponse,Integer Id){
    StudentResponse studentsDB= studentResponseRepository.findById(Id).get();
    studentsDB.setMarks_obtained(studentResponse.getMarks_obtained());
    return studentResponseRepository.save(studentsDB);
    }

    
}
