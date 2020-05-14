package com.nous.survey.surveyapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nous.survey.surveyapp.bean.Answer;
import com.nous.survey.surveyapp.bean.Customer;
import com.nous.survey.surveyapp.bean.Survey;
import com.nous.survey.surveyapp.bean.SurveyResponse;
import com.nous.survey.surveyapp.exception.ResourceNotFoundException;
import com.nous.survey.surveyapp.repository.AnswerRepository;
import com.nous.survey.surveyapp.repository.CustomerRepository;
import com.nous.survey.surveyapp.repository.SurveyRepository;
import com.nous.survey.surveyapp.repository.SurveyResponseRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class SurveyResponseController {

    @Autowired
    private SurveyResponseRepository surveyResponseRepository;

    @Autowired
    private SurveyRepository surveyRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private AnswerRepository answerRepository;
   
    @ApiOperation(value = "find all Survey Responses By Response Id",
    	     notes="find all Survey Response By Responses Id")
    @GetMapping("/responses/id/{id}")
    public Optional<SurveyResponse> getAllSurveyResponsesByResponseId(
    			@ApiParam(value="response Id of Survey Response table",required=true)
    			@PathVariable (value = "id") Long id) {
        return surveyResponseRepository.findById(id);
    }
    
    @ApiOperation(value = "find all Survey Responses By Survey Id",
   	     notes="find all Survey Responses By Survey Id")
    @GetMapping("/surveys/{surveyId}/responses")
    public List<SurveyResponse> getAllSurveyResponsesBySurveyId(
    			@ApiParam(value="surveyId of Survey Response table",required=true)
    			@PathVariable (value = "surveyId") Long surveyId) {
        return surveyResponseRepository.findBySurveyResponseId(surveyId);
    }

    @ApiOperation(value = "create Survey Response",
      	     notes="create Survey Response")
    @PostMapping("/surveys/{surveyId}/responses")
    public SurveyResponse createQuestion(
    			@ApiParam(value="surveyId of Survey Response table",required=true)
    			@PathVariable (value = "surveyId") Long surveyId,
    			@ApiParam(value="customerId of Survey Response table",required=true)
    			@PathVariable (value = "customerId") Long customerId,
    			@ApiParam(value="SurveyResponse",required=true)
                @Valid @RequestBody SurveyResponse response) {
    	return surveyRepository.findById(surveyId).map(survey -> {
            response.setSurvey(survey);
            return surveyResponseRepository.save(response);
        }).orElseThrow(() -> new ResourceNotFoundException("surveyId " + surveyId + " not found"));
    }
    
   
    @ApiOperation(value = "fetch all survey responses",
     	     notes="fetch all survey responses")
    @GetMapping("/responses/all")
    public List<SurveyResponse> getAllResponses() {
    	List<SurveyResponse> responses = surveyResponseRepository.findAll();
        return responses;
    }
    
    @GetMapping("response/survey/{surveyId}")
    public List<SurveyResponse> getAllResponsesBySurveyId(@PathVariable (value = "surveyId") Long surveyId){
    	 List<SurveyResponse> surveyResponseBySurveyUpdated = new ArrayList<SurveyResponse>();
    	 List<SurveyResponse> surveyResponseBySurvey = getAllResponses();
    	 for(SurveyResponse response : surveyResponseBySurvey) {
    		 if(response.getSurvey().getSurveyId() == surveyId ) {
    			 surveyResponseBySurveyUpdated.add(response);
    		 }
    	 }
    	 return surveyResponseBySurveyUpdated;
    }
    
    @ApiOperation(value = "create survey response",
    	     notes="create survey response")
    @PostMapping("/surveys/{surveyId}/customerId/{customerId}/responses")
    public SurveyResponse createSurveyResponse(
    			@ApiParam(value="surveyId of Survey Response table",required=true)
    			@PathVariable (value = "surveyId") Long surveyId,
    			@ApiParam(value="customerId of Survey Response table",required=true)
    			@PathVariable (value = "customerId") Long customerId,
    			@ApiParam(value="surveyResponse",required=true)
    			@Valid @RequestBody SurveyResponse response) {
    	Long cid = response.getCustomer().getCustomerId();
    	System.out.println("cid -> "+cid);
    	
    	Long sid = response.getSurvey().getSurveyId();
    	System.out.println("cid -> "+sid);
    	
    	
    	Optional<Customer> c1 = customerRepository.findById(customerId);
    	Customer c2 = new Customer();
    	if(c1.isPresent()) {
    	    // value is present inside Optional
    	    System.out.println("Value found - " + c1.get());
    	    c2 = c1.get();
    	} else {
    	    // value is absent
    	    System.out.println("Optional is empty");
    	}
    	
    	Optional<Survey> s1 = surveyRepository.findById(surveyId);
    	Survey s2 = new Survey();
    	if(s1.isPresent()) {
    	    // value is present inside Optional
    	    System.out.println("Value found - " + s1.get());
    	    s2 = s1.get();
    	} else {
    	    // value is absent
    	    System.out.println("Optional is empty");
    	}
    	response.setCustomer(c2);
    	response.setSurvey(s2);
    	SurveyResponse response2  = surveyResponseRepository.save(response);
    	long respId = response2.getSurveyResponseId();
    	for(Answer answer : response.getAnswers()) {
    		answer.setResponse(response2);
    		answerRepository.save(answer);
    	}
    	 return surveyResponseRepository.save(response);
    }
    
    @GetMapping("/surveys/response/test")
    public SurveyResponse test2() {
    	
    	
    	SurveyResponse s = new SurveyResponse();
    	s.setSurveyResponseId(222L);
    	s.getCustomer().setCustomerId(1L);
    	s.getSurvey().setSurveyId(1L);
    	Answer a1 = new Answer();
    	a1.setAnswerId(1L);
    	a1.setAnswer("Yes");
    	a1.setRating("5");
    	
    	Answer a2 = new Answer();
    	a2.setAnswerId(2L);
    	a2.setAnswer("Yes");
    	a2.setRating("5");
    	List ansList = new ArrayList<Answer>();
    	ansList.add(a1);ansList.add(a2);
    	s.setAnswers(ansList);
    	return s;
    	
    }
    
    
    
    @PutMapping("/surveys/{surveyId}/responses/{responseId}")
    public SurveyResponse updateQuestion(@PathVariable (value = "surveyId") Long surveyId,
                                 @PathVariable (value = "responseId") Long responseId,
                                 @Valid @RequestBody SurveyResponse responseRequest) {
        if(!surveyRepository.existsById(surveyId)) {
            throw new ResourceNotFoundException("surveyId " + surveyId + " not found");
        }

        return surveyResponseRepository.findById(responseId).map(response -> {
            response.setDescription(responseRequest.getDescription());
            return surveyResponseRepository.save(response);
        }).orElseThrow(() -> new ResourceNotFoundException("responseId " + responseId + "not found"));
    }

//    @DeleteMapping("/surveys/{surveyId}/responses/{responseId}")
//    public ResponseEntity<?> deleteQuestion(@PathVariable (value = "surveyId") Long surveyId,
//                              @PathVariable (value = "responseId") Long responseId) {
//        return surveyResponseRepository.findBySurveyResponseIdAndSurveyId(responseId, surveyId).map(response -> {
//        	surveyResponseRepository.delete(response);
//            return ResponseEntity.ok().build();
//        }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + responseId + " and surveyId " + surveyId));
//    }
}
