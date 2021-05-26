package com.coolshop.integrationtest.stepdef;

import com.coolshop.integrationtest.stepdef.models.CustomerIntegrationTest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class World {
    private CustomerIntegrationTest registeredCustomerRepresentation;
}
