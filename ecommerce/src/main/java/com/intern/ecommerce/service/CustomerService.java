package com.intern.ecommerce.service;

import com.intern.ecommerce.entity.Customer;
import com.intern.ecommerce.exception.CustomerNotFoundException;
import com.intern.ecommerce.exception.ProductNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    public Customer saveCustomer(Customer customer);

    public List<Customer> allCustomers();

    Customer getCustomerById(Long productId) throws CustomerNotFoundException;

    public Customer updateCustomer(Long customerId, Customer customer);

    public void deleteCustomer(Long customerId);
}
