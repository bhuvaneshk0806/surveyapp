package com.nous.survey.surveyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nous.survey.surveyapp.bean.Survey;
import com.nous.survey.surveyapp.exception.ResourceNotFoundException;
import com.nous.survey.surveyapp.repository.SurveyRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class SurveyController {

    @Autowired
    private SurveyRepository surveyRepository;

    @GetMapping("/surveys")
    @ApiOperation(
    		value = "Get All Surveys",
    		notes = "Get All Surveys")
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }
    
    @GetMapping("/surveys/id/{survey_id}")
    @ApiOperation(
    		value="Get survey by Id",
    		notes="Get all surveys for a given survey Id")
    public Optional<Survey> getSurvey(
    		@ApiParam(value = "Survey Id for the surveys you want to retrieve",required = true)
    		@PathVariable Long survey_id) {
         return surveyRepository.findById(survey_id);
    }
    
    @GetMapping("/surveys/names")
    @ApiOperation(
    		value="Get all survey names")
    public List<String> getSurveyNames() {
       return surveyRepository.findAllSurveyNames();
    }
    
//    @GetMapping("/survey/{title}")
//    @ApiOperation(
//    		value="Get survey by survey name")
//    public Survey getSurveyNames(@PathVariable("title") String title) {
//       return surveyRepository.findByTitle(title);
//    }
    
    @GetMapping("/surveys/like/{title}")
    @ApiOperation(
    		value="Get all survey names by title")
    public List<Survey> getSurveyNamesLike(
    		@ApiParam(value = "Title of the survey to be fetched",required = true)
    		@PathVariable String title) {
         return surveyRepository.findByTitleContaining(title);
    }

    @PostMapping("/surveys")
    @ApiOperation(
    		value="Create a survey")
    public Survey createSurvey(
    		@ApiParam(value = "Survey details to be saved",required = true)
    		@Valid @RequestBody Survey survey) {
        return surveyRepository.save(survey);
    }

    @PutMapping("/surveys/{surveyId}")
    @ApiOperation(
    		value="Update a survey")
    public Survey updateSurvey(
    		@ApiParam(value = "Survey Id of which Survey will be updated",required = true)
    		@PathVariable Long surveyId, 
    		@ApiParam(value = "Survey details to be updated",required = true)
    		@Valid @RequestBody Survey surveyRequest) {
        return surveyRepository.findById(surveyId).map(survey -> {
            survey.setTitle(surveyRequest.getTitle());
            survey.setDescription(surveyRequest.getDescription());
                     return surveyRepository.save(survey);
        }).orElseThrow(() -> new ResourceNotFoundException("SurveyId " + surveyId + " not found"));
    }


    @DeleteMapping("/surveys/{surveyId}")
    @ApiOperation(
    		value="Delete a survey by id")
    public ResponseEntity<?> deleteSurvey(
    		@ApiParam(value = "Survey id to delete a survey",required = true)
    		@PathVariable Long surveyId) {
        return surveyRepository.findById(surveyId).map(post -> {
            surveyRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("SurveyId " + surveyId + " not found"));
    }

}
