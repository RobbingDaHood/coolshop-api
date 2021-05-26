package com.coolshop.customer.domain;

import com.coolshop.customer.domain.model.CustomerDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    private CustomerPlugIn customerPlugIn = mock(CustomerPlugIn.class);

    private CustomerService customerService;

    @BeforeEach
    void initUseCase() {
        customerService = new CustomerService(customerPlugIn);
    }

    @Test
    void getCustomer() {
        //Given
        CustomerDomain registeredCustomer = CustomerDomain.builder()
                .id(22L)
                .build();
        when(customerPlugIn.getById(registeredCustomer.getId())).thenReturn(Optional.of(registeredCustomer));

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
        when(customerPlugIn.store(customer)).thenReturn(customer);

        //when
        CustomerDomain result = customerService.registerCustomer(customer);

        //then
        assertEquals(customer, result);
    }
}