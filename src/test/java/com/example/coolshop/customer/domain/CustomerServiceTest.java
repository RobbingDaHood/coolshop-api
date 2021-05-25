package com.example.coolshop.customer.domain;

import com.example.coolshop.customer.api.CustomerExposure;
import com.example.coolshop.customer.domain.model.CustomerDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    private CustomerService customerService;

    @BeforeEach
    void initUseCase() {
        customerService = new CustomerService();
    }

    @Test
    void getCustomer() {
        //Given
        CustomerDomain customerDomain = CustomerDomain.builder()
                .id("22")
                .build();
        customerService.addCustomer(customerDomain);

        //when
        CustomerDomain result = customerService.getCustomer(customerDomain.getId());

        //then
        assertEquals(customerDomain, result);
    }
}