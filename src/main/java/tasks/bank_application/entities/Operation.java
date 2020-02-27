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

    @Override
    public String toString() {
        return String.format("%-10s%-15s%10s%25s%20s%20s%20s", id, operationDate, fromId, toId,
                transAmount, beforeTransfer, afterTransfer);
    }
}
