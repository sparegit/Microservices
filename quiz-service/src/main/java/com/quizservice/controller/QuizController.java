package com.quizservice.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.quizservice.Model.QuestionWrapper;
import com.quizservice.Model.Quiz;
import com.quizservice.Model.QuizDTO;
import com.quizservice.Model.Response;
import com.quizservice.service.QuizService;





@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	
		@PostMapping("/create")
		public ResponseEntity<Quiz> createQuiz(@RequestBody QuizDTO quizdto) {
			
			return quizService.createQuiz(quizdto);
		}
		
		@GetMapping("/get/{id}")
		public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer id){
			
			return quizService.getQuiz(id);
		}
		
		@PostMapping("/submit/{id}")
		public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> responses){
			return quizService.sayTheScore(id,  responses);
		}
	
	
}
