package com.intern.ecommerce.service;

import com.intern.ecommerce.entity.Customer;
import com.intern.ecommerce.entity.Product;
import com.intern.ecommerce.exception.CustomerNotFoundException;
import com.intern.ecommerce.exception.ProductNotFoundException;
import com.intern.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> allCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long customerId) throws CustomerNotFoundException {
        Optional<Customer> product = customerRepository.findById(customerId);
        if(!product.isPresent()){
            throw new CustomerNotFoundException("Customer Not Available");
        }
        return product.get();
    }


    @Override
    public Customer updateCustomer(Long customerId, Customer customer) {
        Customer updatecustomer = customerRepository.findById(customerId).get();
        if(Objects.nonNull(customer.getCustomerName()) && !"".equalsIgnoreCase(customer.getCustomerName())){
            updatecustomer.setCustomerName(customer.getCustomerName());
        }
        if(Objects.nonNull(customer.getMobileNumber()) && !"".equalsIgnoreCase(customer.getMobileNumber())){
            updatecustomer.setMobileNumber(customer.getMobileNumber());
        }
        if(Objects.nonNull(customer.getPassword()) && !"".equalsIgnoreCase(customer.getPassword())){
            updatecustomer.setPassword(customer.getPassword());
        }
        if(Objects.nonNull(customer.getBalance())){
            updatecustomer.setBalance(customer.getBalance());
        }
        return customerRepository.save(updatecustomer);
    }
    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

}
