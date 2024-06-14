package com.finabil.quizv2.repository;

import com.finabil.quizv2.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ResultRepo extends JpaRepository<Result, Integer> {

}