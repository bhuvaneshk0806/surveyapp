package com.nous.survey.surveyapp.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
/**
 * Created by bhuvanesh kadaraiah on 18/02/20.
 */
@Entity
@Table(name = "answer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Answer implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="answer_id")
    private Long answerId;
	
	//@Column(unique = true)
    private String answer;
    @Column(name="question_id")
    private String questionId;
	
	//@Column(unique = true)
    private String rating;
	
	 @ManyToOne(fetch = FetchType.LAZY, optional = false)
	    @JoinColumn(name = "survey_Response_Id", nullable = false)
	    @OnDelete(action = OnDeleteAction.CASCADE)
	   // @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="survey_Response_Id")
	   // @JsonIdentityReference(alwaysAsId=true)
	   // @JsonProperty("survey_Response_Id")
	 @JsonBackReference
	    private SurveyResponse response;

	public SurveyResponse getResponse() {
		return response;
	}

	public void setResponse(SurveyResponse response) {
		this.response = response;
	}

	public Answer() {
		super();
	}

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	
}
