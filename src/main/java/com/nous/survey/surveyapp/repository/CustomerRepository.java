package com.nous.survey.surveyapp.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nous.survey.surveyapp.bean.Customer;

/**
 * Created by bhuvanesh kadaraiah on 18/02/20.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	@Query(value="Select name from customer",nativeQuery = true)
	List<String> findAllCustomers();
	
	@Query(value="Select email from customer where customer_id= ?1",nativeQuery = true)
	String findEmailByCustomerId(Long id);
	
	@Query(value="Select email from customer where name= ?1",nativeQuery = true)
	String findEmailByCustomerName(String name);
	
//	@Query("Select c from customer c where c.name LIKE CONCAT('%',:param,'%')")
//	List<Customer> getCustomerLike(String param);
	
	List<Customer> findByNameContaining(String name);
	
	@Query(value="Select * from customer where name= ?1",nativeQuery = true)
	Customer findByName(String name);
}
