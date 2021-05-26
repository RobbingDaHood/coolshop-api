package com.coolshop.customer.persistance;

import com.coolshop.customer.domain.model.CustomerDomain;
import com.coolshop.customer.domain.CustomerPlugIn;
import com.coolshop.customer.persistance.mappers.CustomerMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CustomerPlugInImpl implements CustomerPlugIn {

    private final CustomerCrudRepository customerCrudRepository;

    public CustomerPlugInImpl(CustomerCrudRepository customerCrudRepository) {
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
