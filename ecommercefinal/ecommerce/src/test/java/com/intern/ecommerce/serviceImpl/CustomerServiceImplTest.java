package com.intern.ecommerce.serviceImpl;

import com.intern.ecommerce.entity.Customer;
import com.intern.ecommerce.repository.CustomerRepository;
import com.intern.ecommerce.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CustomerServiceImplTest {

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;
    Customer customer = Customer.builder().customerId(1L).customerName("AnandhTest").mobileNumber("8976567564").balance(897667L).password("husefi").build();


    @Test
    void getCustomerById() throws Exception {
        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        Long customerId = 1L;
        Customer found = customerService.getCustomerById(customerId);
        assertEquals(found,customer);
    }

    @Test
    void updateCustomer() {
        Long customerId = 1L;
        Customer updatedCustomer = new Customer();
        updatedCustomer.setCustomerId(customerId);
        updatedCustomer.setCustomerName("Anandh A");
        updatedCustomer.setBalance(7878L);
        updatedCustomer.setPassword("newPass");
        updatedCustomer.setMobileNumber("8674567989");
        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        Mockito.when((customerRepository.save(customer))).thenReturn(updatedCustomer);

        Customer result = customerService.updateCustomer(customerId,updatedCustomer);
        assertEquals(updatedCustomer.getCustomerName(),result.getCustomerName());

    }

    @Test
    void deleteCustomer() {
        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        customerService.deleteCustomer(1L);
    }

    @Test
    void getBalanceById() {
        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        Long customerId = 1L;
        Long existingBalance = customerService.getBalanceById(customerId);
        assertEquals(existingBalance,customer.getBalance());
    }

}