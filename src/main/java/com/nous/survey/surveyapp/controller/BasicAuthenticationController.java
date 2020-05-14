package com.nous.survey.surveyapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nous.survey.surveyapp.bean.AuthenticationBean;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BasicAuthenticationController {
	@GetMapping(path="/basicauth")
	public AuthenticationBean helloWorldBean() {
		return new AuthenticationBean("You are authenticated!");
	}
	
	
}
