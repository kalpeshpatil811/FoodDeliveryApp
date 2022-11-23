package com.fooddeliveryapp.service;


import com.fooddeliveryapp.entity.Customer;
import com.fooddeliveryapp.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomerServiceTest {

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    @InjectMocks
    private Customer customer;

    public void setUp() {
        Customer customer=new Customer();
        customer.setCustomerId(1L);
        customer.setFirstName("Ram");
        customer.setLastName("Krishna");
        customer.setMobileNumber("9999999999");
        customer.setEmail("ram@gmail.com");
        customer.setCity("Hyderabad");
        customer.setAddress("Hyderabad");
        customer.setPincode("501505");
    }

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void addCustomerTest() {
        Mockito.doReturn(customer).when(customerRepository).save(Mockito.any());
        assertEquals(customer.getCustomerId(), customerServiceImpl.addCustomer(customer).getCustomerId());
        assertEquals(customer.getFirstName(), customerServiceImpl.addCustomer(customer).getFirstName());
        assertEquals(customer.getLastName(), customerServiceImpl.addCustomer(customer).getLastName());
        assertEquals(customer.getFirstName(), customerServiceImpl.addCustomer(customer).getMobileNumber());
        assertEquals(customer.getFirstName(), customerServiceImpl.addCustomer(customer).getEmail());
        assertEquals(customer.getFirstName(), customerServiceImpl.addCustomer(customer).getCity());
        assertEquals(customer.getFirstName(), customerServiceImpl.addCustomer(customer).getAddress());
        assertEquals(customer.getFirstName(), customerServiceImpl.addCustomer(customer).getPincode());
    }

    @Test
    public void updateCustomerTest() {
        Mockito.doReturn(customer).when(customerRepository).save(Mockito.any());
        assertEquals(customer.getCustomerId(), customerServiceImpl.updateCustomer(customer).getCustomerId());
        assertEquals(customer.getFirstName(), customerServiceImpl.updateCustomer(customer).getFirstName());
        assertEquals(customer.getLastName(), customerServiceImpl.updateCustomer(customer).getLastName());
        assertEquals(customer.getFirstName(), customerServiceImpl.updateCustomer(customer).getMobileNumber());
        assertEquals(customer.getFirstName(), customerServiceImpl.updateCustomer(customer).getEmail());
        assertEquals(customer.getFirstName(), customerServiceImpl.updateCustomer(customer).getCity());
        assertEquals(customer.getFirstName(), customerServiceImpl.updateCustomer(customer).getAddress());
        assertEquals(customer.getFirstName(), customerServiceImpl.updateCustomer(customer).getPincode());
    }
}
