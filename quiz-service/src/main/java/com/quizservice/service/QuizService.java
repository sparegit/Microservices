package com.quizservice.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizservice.Dao.QuizDao;
import com.quizservice.Model.QuestionWrapper;
import com.quizservice.Model.Quiz;
import com.quizservice.Model.QuizDTO;
import com.quizservice.Model.Response;
import com.quizservice.feign.QuizInterface;




@Service
public class QuizService {
	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuizInterface quizInterface;
	
	//	@Autowired
//	QuestionDao questionDao;
	public ResponseEntity<Quiz> createQuiz( QuizDTO quizDto) {
		
		List<Integer> questionNumbers = quizInterface.getQuestionsForQuizService(quizDto.getCategory(), quizDto.getQnum()).getBody();
				
		Quiz quiz = new Quiz();
		quiz.setTitle(quizDto.getTitle());
		quiz.setQuestionIds(questionNumbers);
		quizDao.save(quiz);
		return new ResponseEntity<>(quiz,HttpStatus.CREATED);
	}
	public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {
	Optional<Quiz> quiz =	quizDao.findById(id);
	List<Integer> questionIds = quiz.get().getQuestionIds();
	ResponseEntity<List<QuestionWrapper>> questionsforuser =quizInterface.getQuestions(questionIds);

	
		return questionsforuser;
	}
	public ResponseEntity<Integer> sayTheScore(Integer id, List<Response> responses) {
		
		int result= quizInterface.getScore(responses).getBody();
		
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

	
	
	
	
	

	
}
