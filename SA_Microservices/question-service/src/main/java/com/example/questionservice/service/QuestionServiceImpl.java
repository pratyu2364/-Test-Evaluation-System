package com.example.questionservice.service;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.questionservice.entity.Question;
import com.example.questionservice.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Override
    public Question saveQuestion(Question question){
        return questionRepository.save(question);
    }
    @Override 
    public List<Question> fetchQuestionList(){
        return (List<Question>)questionRepository.findAll();
    }
    @Override
    public void deleteQuestionById(Integer qId)
    {
        questionRepository.deleteById(qId);
    }


    @Override
    public Question findQuestionById(Integer id){

        return questionRepository.findById(id).get();

    }
    @Override
	public Question updateQuestion(Question question) {
    	
		
		  Question existingQuestion=questionRepository.findById(question.getId()).get();
		  existingQuestion.setQuestion_ans(question.getQuestion_ans());
		  existingQuestion.setQuestion_statement(question.getQuestion_statement());
		  existingQuestion.setWeightage(question.getWeightage());
        return  questionRepository.save(question);
        
    }

    
}
