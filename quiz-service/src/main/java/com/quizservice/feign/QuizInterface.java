package com.quizservice.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.quizservice.Model.QuestionWrapper;
import com.quizservice.Model.Response;



@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

	@PostMapping("questions/generate")
	public ResponseEntity< List<Integer>> getQuestionsForQuizService(@RequestParam String category,@RequestParam int numOfQuestions);
	
	@PostMapping("questions/getquestion")
	public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Integer> qIds);
	
	@PostMapping("questions/getscore")
	public ResponseEntity<Integer> getScore(List<Response> response);
}
