package com.example.coolshop.customer.persistance;

import com.example.coolshop.customer.domain.CustomerRepository;
import com.example.coolshop.customer.domain.model.CustomerDomain;
import com.example.coolshop.customer.persistance.mappers.CustomerMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerCrudRepository customerCrudRepository;

    public CustomerRepositoryImpl(CustomerCrudRepository customerCrudRepository) {
        this.customerCrudRepository = customerCrudRepository;
    }

    @Override
    public Optional<CustomerDomain> getById(Long id) {
        return customerCrudRepository.findById(id)
                .map(CustomerMapper::mapToDomain);
    }

    @Override
    public CustomerDomain store(CustomerDomain domain) {
        return CustomerMapper.mapToDomain(
                customerCrudRepository.save(
                        CustomerMapper.mapFromDomain(domain)
                )
        );
    }
}
