package org.example.domain.repository.jdbc;

import org.example.domain.Customer;
import org.example.domain.CustomerRowMapper;
import org.example.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcCustomerRepository implements CustomerRepository {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Customer> getAllCustomers(){

        String sql = "SELECT * FROM customers";

        return jdbcTemplate.query(sql , new CustomerRowMapper());
    }
}
