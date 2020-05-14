package com.nous.survey.surveyapp.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by bhuvanesh kadaraiah on 18/02/20.
 */
@Entity
@Table(name = "customer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long customerId;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String name;
    
    
    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String email;
    
    private String organization;
    
    private String project;
    
    private Date dateOfFeedback;


    
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Customer() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Date getDateOfFeedback() {
		return dateOfFeedback;
	}

	public void setDateOfFeedback(Date dateOfFeedback) {
		this.dateOfFeedback = dateOfFeedback;
	}
   
}
