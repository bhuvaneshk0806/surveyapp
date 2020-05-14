package com.nous.survey.surveyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nous.survey.surveyapp.bean.Customer;
import com.nous.survey.surveyapp.repository.CustomerRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "*")
@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    @ApiOperation(value = "find all customers",
     notes="find all customers")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
    @ApiOperation(value = "find all customers by Id",
    	     notes="find all customers by Id")
    @GetMapping("/customers/id/{customer_id}")
    public Optional<Customer> getCustomer(
    		@ApiParam(value="Customer Id of Customer table",
    		 	required=true)
    		@PathVariable Long customer_id) {
          return customerRepository.findById(customer_id);
    }

    @ApiOperation(value = "Create customer for Survey",
   	     notes="Create customer for Survey")
    @PostMapping("/customers")
    public Customer createCustomer(
    		@ApiParam(value="Customer Details to Save",required=true)
    		@Valid @RequestBody Customer customer) {
        Customer customerUpdated = customerRepository.save(customer);
        return  customerUpdated;
    }
    
    @ApiOperation(value = "find all customer names",
      	     notes="find all customer names")
    @GetMapping("/customer/names")
    public List<String> getCustomerNames() {
    	return customerRepository.findAllCustomers();
    }
    
    @ApiOperation(value = "find customer by customer name",
     	     notes="find customer by customer name")
   @GetMapping("/customer/{name}")
   public Customer getCustomerbyName(@PathVariable("name") String name) {
   	return customerRepository.findByName(name);
   }
    
    @ApiOperation(value = "find all customers matching names",
     	     notes="find all customers matching names")
    @GetMapping("/customer/like/{name}")
    public List<Customer> getCustomerLikeParam(
    		@ApiParam(value="name of the customer to match",required=true)
    		@PathVariable String name) {
    	return customerRepository.findByNameContaining(name);
    }
    
    @ApiOperation(value = "find all customer email matching by id",
    	     notes="find all customer email matching by id")
    @GetMapping("/customer/email/{id}")
    public String getCustomerEmailById(
    		@ApiParam(value="Id of the customer to retrieve email",required=true)
    		@PathVariable Long id) {
    	return customerRepository.findEmailByCustomerId(id);
    }

//    @PutMapping("/customers/{customerId}")
//    public Customer updateCustomer(@PathVariable Long customerId, @Valid @RequestBody Customer customerRequest) {
//        return customerRepository.findById(customerId).map(customer -> {
//            customer.setTitle(customerRequest.getTitle());
//            customer.setDescription(customerRequest.getDescription());
//                     return customerRepository.save(customer);
//        }).orElseThrow(() -> new ResourceNotFoundException("CustomerId " + customerId + " not found"));
//    }
//
//
//    @DeleteMapping("/customers/{customerId}")
//    public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId) {
//        return customerRepository.findById(customerId).map(post -> {
//            customerRepository.delete(post);
//            return ResponseEntity.ok().build();
//        }).orElseThrow(() -> new ResourceNotFoundException("CustomerId " + customerId + " not found"));
//    }

}
