package com.intern.ecommerce.controller;

import com.intern.ecommerce.entity.Customer;
import com.intern.ecommerce.exception.CustomerNotFoundException;
import com.intern.ecommerce.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @PostMapping
    public Customer saveCustomer(@RequestBody Customer customer){
        LOGGER.info("Adding Customer");
        return customerService.saveCustomer(customer);
    }

    @GetMapping
    public List<Customer> allCustomers(){
        LOGGER.info("Fetching Customers");
        return customerService.allCustomers();
    }

    @GetMapping("{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") Long customerId) throws CustomerNotFoundException{
        return customerService.getCustomerById(customerId);
    }

    @PutMapping("{customerId}")
    public Customer updateCustomer(@PathVariable("customerId")Long customerId,@RequestBody Customer customer){
        return customerService.updateCustomer(customerId,customer);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId")Long customerId){
        customerService.deleteCustomer(customerId);
    }

}
