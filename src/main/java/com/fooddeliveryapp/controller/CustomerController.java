package com.fooddeliveryapp.controller;

import com.fooddeliveryapp.entity.Customer;
import com.fooddeliveryapp.exception.CustomerNotFoundException;
import com.fooddeliveryapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer cust){
        return new ResponseEntity<Customer>(customerService.addCustomer(cust), HttpStatus.OK);
    }

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return new ResponseEntity<List<Customer>>(customerService.getAllCustomers(),HttpStatus.OK);
    }

    @GetMapping("customer/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") Long customerId)
            throws CustomerNotFoundException {
        return new ResponseEntity<Customer>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    @PutMapping("customer")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<Customer>(customerService.updateCustomer(customer), HttpStatus.OK);
    }

    @PutMapping("customer/{customerId}")
    public ResponseEntity<Customer> updateCustomerById(@PathVariable("customerId") Long customerId, @RequestBody Customer customer) {
        return new ResponseEntity<Customer>(customerService.updateCustomerById(customerId,customer), HttpStatus.OK);
    }

    @DeleteMapping("customer/{customerId}")
    public ResponseEntity<List<Customer>> deleteCustomer(@PathVariable("customerId") Long customerId)
            throws CustomerNotFoundException {
        List<Customer> customerList = customerService.deleteCustomer(customerId);
        return new ResponseEntity<List<Customer>>(customerList, HttpStatus.OK);
    }

}