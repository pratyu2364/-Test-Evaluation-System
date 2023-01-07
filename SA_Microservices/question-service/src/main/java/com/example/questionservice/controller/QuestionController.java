package com.example.questionservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.questionservice.entity.Question;
import com.example.questionservice.service.QuestionService;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/questions")
@Slf4j
public class QuestionController {
    @Autowired private QuestionService questionService;
    @PostMapping("/")
    public Question saveQuestion(@RequestBody Question question){
        log.info("Inside saveQuestion method of QuestionController");
        return questionService.saveQuestion(question);
    }
    @GetMapping("/list")
    public List<Question> fetchQuestionList(){
        return questionService.fetchQuestionList();
    }
    @GetMapping("/{id}")
    public Question fetchQuestionById(@PathVariable("id") Integer qid){
        return questionService.findQuestionById(qid);
    }

    	    
    @PutMapping("/update")
    public Question upateQuestion(@RequestBody Question question) {
        return questionService.updateQuestion(question);
    }
    
    @DeleteMapping("/delete/{id}")
    public void deleteQuestion(@PathVariable("id") Integer qid) {
        questionService.deleteQuestionById(qid);
    }



    
}
