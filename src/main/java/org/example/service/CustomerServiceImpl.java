package org.example.service;

import org.example.domain.Customer;
import org.example.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }
}
