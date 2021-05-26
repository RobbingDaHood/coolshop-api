package com.example.coolshop.customer.api;

import com.example.coolshop.customer.api.representation.CustomerRepresentation;
import com.example.coolshop.customer.domain.CustomerService;
import com.example.coolshop.customer.domain.model.CustomerDomain;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        CustomerDomain customerDomain = CustomerDomain.builder().id(22L).fullName("Jørgen Petersen").build();
        when(customerService.getCustomer(22L)).thenReturn(customerDomain);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/customers/22"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
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
        //Given
        CustomerRepresentation customerRepresentation = CustomerRepresentation.builder()
                .fullName("Jørgen Petersen")
                .build();

        CustomerDomain registeredCustomerDomain = CustomerDomain.builder()
                .id(22L)
                .fullName(customerRepresentation.getFullName())
                .build();

        ArgumentCaptor<CustomerDomain> customerDomainCapture = ArgumentCaptor.forClass(CustomerDomain.class);
        when(customerService.registerCustomer(customerDomainCapture.capture())).thenReturn(registeredCustomerDomain);

        //When and then
        MvcResult mvcResult = mockMvc.perform(post("/customers")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(customerRepresentation)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andReturn();

        CustomerRepresentation expectedCustomerRepresentation = CustomerRepresentation.builder()
                .id(registeredCustomerDomain.getId())
                .fullName(registeredCustomerDomain.getFullName())
                .build();
        String resultJson = mvcResult.getResponse().getContentAsString();
        String expectedJson = objectMapper.writeValueAsString(expectedCustomerRepresentation);
        assertEquals(expectedJson, resultJson);

        CustomerDomain nonRegisteredCustomerDomain = CustomerDomain.builder()
                .fullName(customerRepresentation.getFullName())
                .build();
        assertEquals(nonRegisteredCustomerDomain, customerDomainCapture.getValue());
    }
}