package com.example.questionservice.service;
import com.example.questionservice.entity.Question;
import java.util.*;
public interface QuestionService {
    Question saveQuestion(Question question);
 
    // Read operation
    List<Question> fetchQuestionList();
 
    // Update operation
    Question updateQuestion(Question question) ;
    // Delete operation
    Question findQuestionById(Integer qId);

    void deleteQuestionById(Integer qId);
    
}
