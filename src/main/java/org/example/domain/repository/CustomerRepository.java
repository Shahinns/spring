package org.example.domain.repository;

import org.example.domain.Customer;


import java.util.List;

public interface CustomerRepository {

    List<Customer> getAllCustomers();
}
