package com.example.coolshop.customer.api;

import com.example.coolshop.customer.api.representation.CustomerRepresentation;
import com.example.coolshop.customer.domain.CustomerService;
import com.example.coolshop.customer.domain.model.CustomerDomain;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CustomerExposure.class)
class CustomerDomainRepresentationExposureTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;

    @Test
    void getCustomer() throws Exception {
        CustomerDomain customerDomain = CustomerDomain.builder().id("22").fullName("Jørgen Petersen").build();
        when(customerService.getCustomer("22")).thenReturn(customerDomain);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/customers/22"))
                .andExpect(status().isOk())
                .andReturn();

        CustomerRepresentation expectedCustomerRepresentation = CustomerRepresentation.builder()
                .id(customerDomain.getId())
                .fullName(customerDomain.getFullName())
                .build();
        String resultJson = mvcResult.getResponse().getContentAsString();
        String expectedJson = objectMapper.writeValueAsString(expectedCustomerRepresentation);
        assertEquals(expectedJson, resultJson);
    }

    @Test
    void postCustomer() throws Exception {
        CustomerRepresentation customerRepresentation = CustomerRepresentation.builder()
                .fullName("Jørgen Petersen")
                .build();

        mockMvc.perform(post("/customers")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(customerRepresentation)))
                .andExpect(status().isOk());

        ArgumentCaptor<CustomerDomain> customerDomainCapture = ArgumentCaptor.forClass(CustomerDomain.class);
        verify(customerService, Mockito.times(1)).registerCustomer(customerDomainCapture.capture());
        Assertions.assertEquals(customerRepresentation.getFullName(), customerDomainCapture.getValue().getFullName());
    }
}