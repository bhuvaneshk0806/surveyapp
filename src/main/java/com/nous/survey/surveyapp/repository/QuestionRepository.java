package com.nous.survey.surveyapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.survey.surveyapp.bean.Question;

import java.util.List;
import java.util.Optional;

/**
 * Created by bhuvanesh kadaraiah on 18/02/20.
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
   // List<Question> findBySurveyId(Long surveyId);
   // Optional<Question> findByIdAndSurveyId(Long id, Long surveyId);
}
