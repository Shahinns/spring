package org.example.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CustomerRowMapper implements RowMapper<Customer>  {


    @Override

    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

        Customer customer = new Customer();

        customer.setCustomer_Id(rs.getString("customer_id"));
        customer.setName(rs.getString("name"));
        customer.setAddress(rs.getString("address"));
        customer.setNoOfOrdersMade(rs.getInt("no_of_orders_made"));

        return customer;
    }

}
