package com.bankapp.entities.dto;

import com.bankapp.constants.Currencies;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreateRequest {
    private String clientName;
    private BigDecimal amount;
    private Currencies accCode;
}
