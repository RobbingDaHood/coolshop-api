package com.example.coolshop.customer.api;

import com.example.coolshop.customer.api.representation.CustomerRepresentation;
import com.example.coolshop.customer.domain.CustomerService;
import com.example.coolshop.customer.domain.model.CustomerDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class CustomerDomainRepresentationExposureTest {

    private CustomerService customerService = Mockito.mock(CustomerService.class);

    private CustomerExposure customerExposure;

    @BeforeEach
    void initUseCase() {
        customerExposure = new CustomerExposure(customerService);
    }

    @Test
    void getCustomer() {
        //Given
        when(customerService.getCustomer("22")).thenReturn(CustomerDomain.builder()
                .id("22")
                .build());

        //When
        CustomerRepresentation result = customerExposure.getCustomer("22");

        //Then
        assertEquals("22", result.getId());
    }
}