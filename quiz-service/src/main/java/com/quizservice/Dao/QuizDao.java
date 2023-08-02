package com.quizservice.Dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.quizservice.Model.Quiz;




public interface QuizDao extends JpaRepository<Quiz, Integer> {



}
