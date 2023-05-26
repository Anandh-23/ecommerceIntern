package com.intern.ecommerce.serviceImpl;

import com.intern.ecommerce.entity.Customer;
import com.intern.ecommerce.exception.CustomerNotCreatedException;
import com.intern.ecommerce.exception.CustomerNotFoundException;
import com.intern.ecommerce.repository.CustomerRepository;
import com.intern.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Customer saveCustomer(Customer customer) throws Exception {
        Customer savedCustomer =  customerRepository.save(customer);
        if(savedCustomer == null)
            throw new CustomerNotCreatedException("Customer Not Created");
        return savedCustomer;
    }

    @Override
    public List<Customer> allCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long customerId) throws Exception {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(!customer.isPresent()){
            throw new CustomerNotFoundException("Customer Not Available");
        }
        return customer.get();
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

    @Override
    public Long getBalanceById(Long customerId) {
        return customerRepository.findById(customerId).get().getBalance();
    }

    @Override
    public Long addBalanceById(Long customerId,Long topUp) {
        Customer customer = customerRepository.findById(customerId).get();
        Long newBalance = getBalanceById(customerId)+topUp;
        customer.setBalance(newBalance);
        customerRepository.save(customer);
        return newBalance;
    }

}
