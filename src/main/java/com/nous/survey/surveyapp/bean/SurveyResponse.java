package com.nous.survey.surveyapp.bean;


import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Created by bhuvanesh kadaraiah on 18/02/20.
 */
@Entity
@Table(name = "surveyResponse")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SurveyResponse implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="survey_Response_Id")
    private Long surveyResponseId;

    //@NotNull
    @Lob
    private String description;
    
    @OneToMany(mappedBy = "response")
    @JsonManagedReference
    private List<Answer> answers;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "survey_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    //@JsonIdentityReference(alwaysAsId=true)
    //@JsonProperty("survey_id")
    private Survey survey = new Survey();
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    //@JsonIdentityReference(alwaysAsId=true)
    //@JsonProperty("customer_id")
    private Customer customer = new Customer();

    
   public Long getSurveyResponseId() {
		return surveyResponseId;
	}

	public void setSurveyResponseId(Long surveyResponseId) {
		this.surveyResponseId = surveyResponseId;
	}

public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

}
