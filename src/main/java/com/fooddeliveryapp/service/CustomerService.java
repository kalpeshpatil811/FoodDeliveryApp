package com.fooddeliveryapp.service;


import com.fooddeliveryapp.entity.Customer;
import com.fooddeliveryapp.exception.CustomerNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public interface CustomerService {

    public Customer addCustomer(Customer cust);

    public List<Customer> getAllCustomers();

    public Customer getCustomerById(Long customerId) throws CustomerNotFoundException;

    public Customer updateCustomer(Customer customer);

    public Customer updateCustomerById(Long customerId, Customer customer);

    public List<Customer> deleteCustomer(Long customerId) throws CustomerNotFoundException;

}
