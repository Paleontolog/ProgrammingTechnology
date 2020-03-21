package com.bankapp.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OperationDTO {
    private Long id;
    private Date operationDate;
    private Long fromUser;
    private Long toUser;
    private BigDecimal transAmount;
    private BigDecimal beforeTransfer;
    private BigDecimal afterTransfer;
}
