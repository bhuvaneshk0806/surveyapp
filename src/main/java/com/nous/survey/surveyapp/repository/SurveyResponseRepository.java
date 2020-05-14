package com.nous.survey.surveyapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.survey.surveyapp.bean.SurveyResponse;

import java.util.List;
import java.util.Optional;

/**
 * Created by bhuvanesh kadaraiah on 18/02/20.
 */
@Repository
public interface SurveyResponseRepository extends JpaRepository<SurveyResponse, Long> {
    List<SurveyResponse> findBySurveyResponseId(Long surveyId);
   // Optional<SurveyResponse> findBySurveyResponseIdAndSurveyId(Long surveyResponseId, Long surveyId);
}
