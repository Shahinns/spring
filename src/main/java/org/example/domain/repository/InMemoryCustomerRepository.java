package org.example.domain.repository;

import org.example.domain.Customer;
import org.example.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

    private List<Customer> listOfCustomers = new ArrayList<Customer>();
    public InMemoryCustomerRepository(){



        Customer customer1 = new Customer("C001", "John Smith", "New York, USA", 3);
        listOfCustomers.add(customer1);

        Customer customer2 = new Customer("C002", "Emily Johnson", "Los Angeles, USA", 5);
        listOfCustomers.add(customer2);

        Customer customer3 = new Customer("C003", "Michael Brown", "Chicago, USA", 2);
        listOfCustomers.add(customer3);

        Customer customer4 = new Customer("C004", "Sarah Davis", "Houston, USA", 7);
        listOfCustomers.add(customer4);

        Customer customer5 = new Customer("C005", "James Wilson", "Phoenix, USA", 1);
        listOfCustomers.add(customer5);


    }







    public List<Customer> getAllCustomers(){

        return listOfCustomers;
    }
}
