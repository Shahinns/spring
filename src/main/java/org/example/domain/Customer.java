package org.example.domain;

public class Customer {

    //customerId, name, address, and noOfOrdersMade
    private String customer_Id;
    private String name;
    private String address;
    private int noOfOrdersMade;

    public String getCustomer_Id() {
        return customer_Id;
    }

    public void setCustomer_Id(String customer_Id) {
        this.customer_Id = customer_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNoOfOrdersMade() {
        return noOfOrdersMade;
    }

    public void setNoOfOrdersMade(int noOfOrdersMade) {
        this.noOfOrdersMade = noOfOrdersMade;
    }

    public Customer(String customer_Id, String name, String address, int noOfOrdersMade) {
        this.customer_Id = customer_Id;
        this.name = name;
        this.address = address;
        this.noOfOrdersMade = noOfOrdersMade;
    }

}
