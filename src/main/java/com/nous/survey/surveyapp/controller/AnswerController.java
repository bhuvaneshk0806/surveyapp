package com.nous.survey.surveyapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nous.survey.surveyapp.bean.Answer;
import com.nous.survey.surveyapp.exception.ResourceNotFoundException;
import com.nous.survey.surveyapp.repository.AnswerRepository;
import com.nous.survey.surveyapp.repository.SurveyResponseRepository;

import java.util.List;

import javax.validation.Valid;

@RestController
public class AnswerController {

    @Autowired
    private SurveyResponseRepository surveyResponseRepository;
    
    @Autowired
    private AnswerRepository answerRepository;

       
//    @GetMapping("/response/{responseId}/answers")
//    public List<Answer> getAllQuestionsBySurveyId(@PathVariable (value = "responseId") Long responseId) {
//        return answerRepository.findBySurveyResponseId(responseId);
//    }

    @PostMapping("/response/{responseId}/answers")
    public Answer createQuestion(@PathVariable (value = "responseId") Long responseId,
                                 @Valid @RequestBody Answer answer) {
    	return surveyResponseRepository.findById(responseId).map(response -> {
    		answer.setResponse(response);
            return answerRepository.save(answer);
        }).orElseThrow(() -> new ResourceNotFoundException("responseId " + responseId + " not found"));
    }
    
   
    
    @GetMapping("/answers/all")
    public List<Answer> getAllQuestions() {
        return answerRepository.findAll();
    }
    
//    @PostMapping("/response/{responseId}/customerId/{customerId}/answers")
//    public Answer test(@PathVariable (value = "responseId") Long responseId,@PathVariable (value = "customerId") Long customerId,
//            @Valid @RequestBody Answer response) {
//    	Optional<Customer> c1 = customerRepository.findById(customerId);
//    	Customer c2 = new Customer();
//    	if(c1.isPresent()) {
//    	    // value is present inside Optional
//    	    System.out.println("Value found - " + c1.get());
//    	    c2 = c1.get();
//    	} else {
//    	    // value is absent
//    	    System.out.println("Optional is empty");
//    	}
//    	
//    	Optional<Survey> s1 = surveyRepository.findById(responseId);
//    	Survey s2 = new Survey();
//    	if(s1.isPresent()) {
//    	    // value is present inside Optional
//    	    System.out.println("Value found - " + s1.get());
//    	    s2 = s1.get();
//    	} else {
//    	    // value is absent
//    	    System.out.println("Optional is empty");
//    	}
//    	response.setCustomer(c2);
//    	response.setSurvey(s2);
//    	 return surveyResponseRepository.save(response);
//    }
    
    
    
//    @PutMapping("/response/{responseId}/answers/{answerId}")
//    public Answer updateQuestion(@PathVariable (value = "responseId") Long responseId,
//                                 @PathVariable (value = "responseId") Long answerId,
//                                 @Valid @RequestBody Answer responseRequest) {
//        if(!surveyRepository.existsById(responseId)) {
//            throw new ResourceNotFoundException("responseId " + responseId + " not found");
//        }
//
//        return surveyResponseRepository.findById(responseId).map(response -> {
//            response.setDescription(responseRequest.getDescription());
//            return surveyResponseRepository.save(response);
//        }).orElseThrow(() -> new ResourceNotFoundException("responseId " + responseId + "not found"));
//    }

//    @DeleteMapping("/response/{responseId}/answers/{answerId}")
//    public ResponseEntity<?> deleteQuestion(@PathVariable (value = "responseId") Long responseId,
//                              @PathVariable (value = "responseId") Long answerId) {
//        return surveyResponseRepository.findByIdAndSurveyId(responseId, responseId).map(response -> {
//        	surveyResponseRepository.delete(response);
//            return ResponseEntity.ok().build();
//        }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + responseId + " and responseId " + responseId));
 //   }
}
