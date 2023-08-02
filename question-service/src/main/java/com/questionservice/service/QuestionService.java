package com.questionservice.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.questionservice.Dao.QuestionDao;
import com.questionservice.model.Question;
import com.questionservice.model.QuestionWrapper;
import com.questionservice.model.Response;




@Service
public class QuestionService {
	
	@Autowired
	QuestionDao questionDao;
	
	public  ResponseEntity<List<Question>> getAllQuestions() {
		List<Question> result =  questionDao.findAll();
		return new ResponseEntity<List<Question>>(result,HttpStatus.OK);
	}

	public List<Question> getByCategory(String category) {
		
		return questionDao.findByCategory(category);
	}

	public Question createQuestion(Question question) {
		
		return questionDao.save(question);
	}

	public ResponseEntity <List<Integer>> getQuestionsForQuizService(String category,int numOfQuestions) {
		
		List<Integer> result =  questionDao.findRandomQuestionsByCategory(category, numOfQuestions);
		
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestions(List<Integer> qIds) {
		List<QuestionWrapper> qWrappers = new ArrayList<>();
		List<Question> questions = new ArrayList<>();
		for(Integer id : qIds) {
			questions.add(questionDao.findById(id).get());
		}
		
		for(Question question: questions) {
			QuestionWrapper qw = new QuestionWrapper();
			qw.setId(question.getId());
			qw.setQuestionTitle(question.getQuestionTitle());
			qw.setOption1(question.getOption1());
			qw.setOption2(question.getOption2());
			qw.setOption3(question.getOption3());
			qw.setOption4(question.getOption4());
			qWrappers.add(qw);
		}
		return new ResponseEntity<List<QuestionWrapper>>(qWrappers,HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Response> response) {
		int score =0;
		for(Response res : response) {
			Question question = questionDao.findById(res.getId()).get();
			if(question.getRightAns().equals(res.getResponse())) {
				score++;
			}
			
		}
		return new ResponseEntity<>(score,HttpStatus.OK);
	}
	
	

	
}
