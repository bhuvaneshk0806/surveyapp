package com.nous.survey.surveyapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.nous.survey.surveyapp.bean.Customer;
import com.nous.survey.surveyapp.bean.Survey;
import com.nous.survey.surveyapp.repository.CustomerRepository;
import com.nous.survey.surveyapp.repository.SurveyRepository;

@Service
public class NotificationService {
	
	
	private JavaMailSender javaMailService;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private SurveyRepository surveyRepository;
	
	@Autowired
	public NotificationService(JavaMailSender javaMailService) {
		this.javaMailService=javaMailService;
	}
	
	public void sendNotification(String SurveyName,String custName) throws MailException
	{
		SimpleMailMessage message = new SimpleMailMessage();
		String email = customerRepository.findEmailByCustomerName(custName);
		Customer customer = customerRepository.findByName(custName);
		Survey survey = surveyRepository.findByTitle(SurveyName);
		message.setSubject("Nous Customer Experience Management(CSAT) - " + SurveyName );
		message.setTo(email);
		//message.setFrom(user.getEmailAddress());
		message.setText(""
				+ "http://localhost:3000/newSurvey/customer/"
				+custName+"/"+customer.getCustomerId()+"/survey/"+SurveyName+"/"+survey.getSurveyId());
		javaMailService.send(message);
		
	}
	
	
}
