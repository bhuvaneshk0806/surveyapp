package com.nous.survey.surveyapp.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nous.survey.surveyapp.bean.Survey;

/**
 * Created by bhuvanesh kadaraiah on 18/02/20.
 */
@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
	@Query(value = "Select title from survey",nativeQuery = true)
	List<String> findAllSurveyNames();
	List<Survey> findByTitleContaining(String name);
	
	Survey findByTitle(String title);
}
