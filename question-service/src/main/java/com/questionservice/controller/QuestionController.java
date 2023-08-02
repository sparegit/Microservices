package com.questionservice.controller;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.questionservice.model.Question;
import com.questionservice.model.QuestionWrapper;
import com.questionservice.model.Response;
import com.questionservice.service.QuestionService;



@RestController
@RequestMapping("questions")
public class QuestionController {
	@Autowired
	QuestionService questionService;
	
	@GetMapping("all")
	public ResponseEntity<List<Question>> getAllQuestions(){
		return questionService.getAllQuestions();
	}
	
	@GetMapping("category/{category}")
	public List<Question> getByCategory(@PathVariable String category){
		return questionService.getByCategory(category);
	}
	
	@PostMapping("add")
	public Question createQuestion(@RequestBody Question question) {
		return questionService.createQuestion(question);
	}
	
	@PostMapping("generate")
	public ResponseEntity< List<Integer>> getQuestionsForQuizService(@RequestParam String category,@RequestParam int numOfQuestions){
	    
		
		return questionService.getQuestionsForQuizService(category,numOfQuestions); 
	}
	
	@PostMapping("getquestion")
	public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Integer> qIds){
		return questionService.getQuestions(qIds);
	}
	
	@PostMapping("getscore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> response){
		
		return questionService.getScore(response);
	}
	
}
