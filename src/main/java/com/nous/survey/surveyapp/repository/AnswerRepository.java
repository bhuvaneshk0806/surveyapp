package com.nous.survey.surveyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.survey.surveyapp.bean.Answer;

import java.util.List;
import java.util.Optional;

/**
 * Created by bhuvanesh kadaraiah on 18/02/20.
 */
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
   // List<Answer> findBySurveyResponseId(Long responseId);
   // Optional<Answer> findByIdAndResponseId(Long id, Long responseId);
}
