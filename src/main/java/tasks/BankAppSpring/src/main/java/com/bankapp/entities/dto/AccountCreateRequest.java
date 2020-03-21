package com.bankapp.entities.dto;

import com.bankapp.constants.Currencies;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreateRequest {
    @JsonIgnore
    private String clientName;
    private BigDecimal amount;
    private Currencies accCode;
}
