package com.example.coolshop.integrationtest.stepdef.models;

import lombok.*;

@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
public class CustomerIntegrationTest {
    private Long id;
    private String fullName;
}
