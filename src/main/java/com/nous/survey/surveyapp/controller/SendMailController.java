package com.nous.survey.surveyapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nous.survey.surveyapp.service.NotificationService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@RequestMapping("/sendEmail")
@CrossOrigin(origins = "http://localhost:3000")
public class SendMailController {
	@Autowired
	private NotificationService notificationService;
	
Logger logger = LoggerFactory.getLogger(SendMailController.class);
	

	@GetMapping("/surveyName/{surveyName}/customer/{custName}")
	@ApiOperation(value = "Send notification Mail",
			notes="Send notification email for customer to notify about a survey")
	public void sendMail(@ApiParam(value = "survey Name for which this survey response belongs to",required=true)
						 @PathVariable String surveyName,
						 @ApiParam(value = "Customer Name who is providing survey response",required=true)
						 @PathVariable String custName) {
//		User user = new User();
//		user.setFirstName("Bhuvanesh");
//		user.setLastName("Kadaraiah");
//		user.setEmailAddress("bhuvan080682@gmail.com");
		try {
			notificationService.sendNotification(surveyName,custName);
		}catch(MailException exception) {
			logger.info("Error sending email : " + exception.getMessage());
		}
		
	}
}
