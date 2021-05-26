package com.example.coolshop.customer.domain;

import com.example.coolshop.customer.domain.model.CustomerDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    private CustomerRepository customerRepository = mock(CustomerRepository.class);

    private CustomerService customerService;

    @BeforeEach
    void initUseCase() {
        customerService = new CustomerService(customerRepository);
    }

    @Test
    void getCustomer() {
        //Given
        CustomerDomain registeredCustomer = CustomerDomain.builder()
                .id(22L)
                .build();
        when(customerRepository.getById(registeredCustomer.getId())).thenReturn(Optional.of(registeredCustomer));

        //when
        Optional<CustomerDomain> result = customerService.getCustomer(registeredCustomer.getId());

        //then
        assertTrue(result.isPresent());
        assertEquals(registeredCustomer, result.get());
    }

    @Test
    void storeCustomer() {
        //Given
        CustomerDomain customer = CustomerDomain.builder().build();
        when(customerRepository.store(customer)).thenReturn(customer);

        //when
        CustomerDomain result = customerService.registerCustomer(customer);

        //then
        assertEquals(customer, result);
    }
}