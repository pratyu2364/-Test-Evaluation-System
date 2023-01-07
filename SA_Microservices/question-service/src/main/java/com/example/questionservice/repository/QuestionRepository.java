package com.example.questionservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.questionservice.entity.Question;
@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    
}
