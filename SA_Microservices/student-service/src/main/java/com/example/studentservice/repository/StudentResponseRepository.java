package com.example.studentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.studentservice.entity.StudentResponse;

import java.util.List;
@Repository
public interface StudentResponseRepository extends JpaRepository<StudentResponse,Integer> {
    public List<StudentResponse> findByStudentId(int student_id);
}
