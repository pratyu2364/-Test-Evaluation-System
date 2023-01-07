package com.example.evaluation.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.evaluation.entity.Student;
import com.example.evaluation.entity.StudentResponse;
@RestController
@RequestMapping("/evaluate")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EvaluatorController {
    @Autowired private WebClient webClient;
    @GetMapping("/")
    public List<Student> Evaluate_all(){ 

      //ObjectMapper mapper = new ObjectMapper();
      /*
    Mono <Question[]> response = webClient.get().uri("http://localhost:8082/questions/list").retrieve().bodyToMono(Question[].class);
    Question[] objects = response.block();
  
    List<Question>q_list =Arrays.stream(objects)
  .map(object -> mapper.convertValue(object, Question.class))
  .collect(Collectors.toList());**/
  //List<StudentResponse>s_list = webClient.get().uri("http://localhost:8083/responses/list").retrieve().bodyToFlux(StudentResponse.class).collectList().block();
  List<Student>student_list = webClient.get().uri("http://localhost:9091/students/list").retrieve().bodyToFlux(Student.class).collectList().block();
  //StudentResponse[] objects2 = response2.block();  
  //List<StudentResponse>s_list =Arrays.stream(objects2).map(object -> mapper.convertValue(object, StudentResponse.class)).collect(Collectors.toList());
  int student_marks = 0;
  int srid;
  int sid;
  for(int i = 0;i<student_list.size();i++){
    Student s = student_list.get(i);
    sid = s.getId();
    student_marks = 0;
    List<StudentResponse>s_list = webClient.get().uri("http://localhost:9091/responses/"+sid).retrieve().bodyToFlux(StudentResponse.class).collectList().block();
    for(int j = 0;j<s_list.size();j++){
      StudentResponse sr = s_list.get(j);
      String student_ans  = sr.getResponse();
      String actual_ans = sr.getQuestion().getQuestion_ans();
      int w = sr.getQuestion().getWeightage();
      if(student_ans.equals(actual_ans)){
        sr.setMarks_obtained(w);
        student_marks+=w;
      }
      else{
        sr.setMarks_obtained(0);
      }
      srid = sr.getId();
      webClient.put().uri("http://localhost:9091/responses/update/"+srid).bodyValue(sr).retrieve().bodyToMono(StudentResponse.class).block();
    }
    s.setMarks_obtained(student_marks);
    webClient.put().uri("http://localhost:9091/students/update/"+sid).bodyValue(s).retrieve().bodyToMono(Student.class).block();
  }


  return student_list;   
}
@GetMapping("/{studentId}")
public Student Evaluate_Student_by_id(@PathVariable(value="studentId") Integer Id){
  int student_marks = 0;
  int srid;
     Student s =  webClient.get().uri("http://localhost:9091/students/"+Id).retrieve().bodyToMono(Student.class).block();
    List<StudentResponse>s_list = webClient.get().uri("http://localhost:9091/responses/"+Id).retrieve().bodyToFlux(StudentResponse.class).collectList().block();
    for(int j = 0;j<s_list.size();j++){
      StudentResponse sr = s_list.get(j);
      String student_ans  = sr.getResponse();
      String actual_ans = sr.getQuestion().getQuestion_ans();
      int w = sr.getQuestion().getWeightage();
      if(student_ans.equals(actual_ans)){
        sr.setMarks_obtained(w);
        student_marks+=w;
      }
      else{
        sr.setMarks_obtained(0);
      }
      srid = sr.getId();
      webClient.put().uri("http://localhost:8083/responses/update/"+srid).bodyValue(sr).retrieve().bodyToMono(StudentResponse.class).block();
    }
    s.setMarks_obtained(student_marks);
    webClient.put().uri("http://localhost:8083/students/update/"+Id).bodyValue(s).retrieve().bodyToMono(Student.class).block();
    return s;
  }


 
}



