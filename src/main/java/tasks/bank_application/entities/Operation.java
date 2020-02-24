package tasks.bank_application.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Operation {
    private Long id;
    private Date operationDate;
    private Long fromId;
    private Long toId;
    private BigDecimal transAmount;
    private BigDecimal beforeTransfer;
    private BigDecimal afterTransfer;
}
