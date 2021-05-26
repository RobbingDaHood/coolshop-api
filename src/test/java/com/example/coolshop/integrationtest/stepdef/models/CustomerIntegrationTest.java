package com.example.coolshop.integrationtest.stepdef.models;

import lombok.*;

@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
public class CustomerIntegrationTest {
    private String id;
    private String fullName;
}
