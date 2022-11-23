package com.fooddeliveryapp.service;

import com.fooddeliveryapp.entity.Cart;
import com.fooddeliveryapp.entity.Customer;
import com.fooddeliveryapp.exception.CustomerNotFoundException;
import com.fooddeliveryapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository custRepo;

    public Customer addCustomer(Customer cust){
        Cart cart = new Cart();
        cust.setCart(cart);
        return custRepo.save(cust);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return custRepo.findAll();
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return custRepo.save(customer);
    }

    @Override
    public Customer updateCustomerById(Long customerId, Customer customer) {
       Customer cust = custRepo.findById(customerId).get();
       cust.setFirstName(customer.getFirstName());
       cust.setLastName(customer.getLastName());
       cust.setMobileNumber(customer.getMobileNumber());
       cust.setEmail(customer.getEmail());
       cust.setCity(customer.getCity());
       cust.setAddress(customer.getAddress());
       cust.setPincode(customer.getPincode());

       Customer updatedCustomer = custRepo.save(cust);

        return custRepo.save(updatedCustomer);
    }

    @Override
    public Customer getCustomerById(Long customerId) throws CustomerNotFoundException {
        try {
            Customer customer = custRepo.findById(customerId).get();
            return customer;
        } catch (Exception e) {
            throw new CustomerNotFoundException("Id is not present, enter correct Id");
        }
    }



    @Override
    public List<Customer> deleteCustomer(Long customerId) throws CustomerNotFoundException {
        try {
            custRepo.deleteById(customerId);
            return custRepo.findAll();
        } catch (Exception e) {
            throw new CustomerNotFoundException("Id is not present, enter correct Id");
        }
    }

}
