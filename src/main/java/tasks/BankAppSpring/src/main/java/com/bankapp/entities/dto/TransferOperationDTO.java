package com.bankapp.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferOperationDTO {
    private Long fromAccountId;
    private String toNumber;
    private BigDecimal transAmount;
}
