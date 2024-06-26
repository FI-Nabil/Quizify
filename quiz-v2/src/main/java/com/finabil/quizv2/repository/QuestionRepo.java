package com.finabil.quizv2.repository;

import com.finabil.quizv2.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {

}