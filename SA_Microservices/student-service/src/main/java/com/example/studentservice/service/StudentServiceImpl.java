package com.example.studentservice.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.studentservice.entity.Student;
import com.example.studentservice.repository.StudentRepository;

@Service
public class StudentServiceImpl  implements StudentService{
    @Autowired
    private StudentRepository  studentRepository;
    @Override
    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }
    @Override
    public void deleteStudentById(Integer Id)
    {
        studentRepository.deleteById(Id);
    }
    @Override
    public Student findStudentById(Integer id){

        return studentRepository.findById(id).get();

    }
    @Override
    public Student updateStudent(Student student,Integer Id){
        Student studentsDB= studentRepository.findById(Id).get();
        studentsDB.setMarks_obtained(student.getMarks_obtained());
        return studentRepository.save(studentsDB);
        }

        @Override
        public List<Student>findAll(){
            return studentRepository.findAll();
        }

    
}
