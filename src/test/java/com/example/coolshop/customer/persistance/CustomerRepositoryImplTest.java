package com.example.coolshop.customer.persistance;

import com.example.coolshop.customer.domain.model.CustomerDomain;
import com.example.coolshop.customer.persistance.entities.CustomerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerRepositoryImplTest {
    private final CustomerCrudRepository customerCrudRepository = mock(CustomerCrudRepository.class);

    private CustomerRepositoryImpl customerRepository;

    @BeforeEach
    void initUseCase() {
        customerRepository = new CustomerRepositoryImpl(customerCrudRepository);
    }

    @Test
    void getById() {
        CustomerEntity customerEntity = CustomerEntity.builder()
                .id(22L)
                .fullName("Jørgen Petersen")
                .build();

        when(customerCrudRepository.findById(customerEntity.getId())).thenReturn(java.util.Optional.of(customerEntity));

        Optional<CustomerDomain> result = customerRepository.getById(customerEntity.getId());

        assertTrue(result.isPresent());
        assertEquals(customerEntity.getId(), result.get().getId());
        assertEquals(customerEntity.getFullName(), result.get().getFullName());
    }

    @Test
    void store() {
        CustomerDomain customerDomain = CustomerDomain.builder()
                .fullName("Jørgen Petersen")
                .build();
        ;
        CustomerEntity customerEntity = CustomerEntity.builder()
                .fullName("Jørgen Petersen")
                .build();
        ;
        CustomerEntity registeredCustomerEntity = CustomerEntity.builder()
                .id(22L)
                .fullName("Jørgen Petersen")
                .build();

        when(customerCrudRepository.save(customerEntity)).thenReturn(registeredCustomerEntity);

        CustomerDomain result = customerRepository.store(customerDomain);

        assertEquals(registeredCustomerEntity.getId(), result.getId());
        assertEquals(registeredCustomerEntity.getFullName(), result.getFullName());
    }
}