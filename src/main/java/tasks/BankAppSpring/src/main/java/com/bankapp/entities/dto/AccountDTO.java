package com.bankapp.entities.dto;

import com.bankapp.constants.Currencies;
import lombok.*;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDTO {
    private Long id;
    private Long clientId;
    private BigDecimal amount;
    private Currencies accCode;
}
