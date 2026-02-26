package org.example.domain.repository;

import org.example.domain.Customer;
import org.example.domain.Product;

import java.util.List;

public interface CustomerRepository {

    List<Customer> getAllCustomers();
}
