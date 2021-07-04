package service;

import model.Customer;

import java.util.*;

public class CustomerService {
    public Map<String, Customer> customers = new HashMap<>();

    //Singleton declarations starts
    private static final CustomerService customerService = new CustomerService( );
    private CustomerService() { }
    public static CustomerService getCustomerService( ) { return customerService; }
    //Singleton declarations ends

    public void addCustomer(String email, String firstName, String lastName){
        customers.put(email, new Customer(firstName, lastName, email));
    }

    public Customer getCustomer(String customerEmail){
        if (!customers.containsKey(customerEmail)) {
                throw new IllegalArgumentException("Customer Id not found");
            }
        return customers.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers(){
        return customers.values();
    }
}
