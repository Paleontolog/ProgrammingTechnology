package com.bankapp.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OneAccountOperationDTO {
    private Long accountId;
    private BigDecimal value;
}

