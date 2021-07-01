package service;

import model.Customer;

import java.util.*;

public class CustomerService {
    Map<String, Customer> customers = new HashMap<String, Customer>();

    private static final CustomerService customerService = new CustomerService( );
    private CustomerService() { }

    public static CustomerService getCustomerService( ) {
        return customerService;
    }

    public void addCustomer(String email, String firstName, String lastName){
        Customer customer = new Customer(firstName, lastName, email);
        customers.put(customer.email, customer);
    }
    public Customer getCustomer(String customerEmail){
        return customers.get(customerEmail);
    }
    public Collection<Customer> getAllCustomers(){
        return new LinkedList<>(customers.values());
    }
}
