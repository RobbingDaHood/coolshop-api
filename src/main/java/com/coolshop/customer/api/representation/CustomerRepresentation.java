package com.coolshop.customer.api.representation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class CustomerRepresentation {
    private final Long id;
    @NotBlank(message = "Full name is mandatory")
    private final String fullName;
}
