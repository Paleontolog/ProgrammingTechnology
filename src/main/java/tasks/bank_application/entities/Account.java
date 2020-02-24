package tasks.bank_application.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import tasks.bank_application.constants.Currencies;
import tasks.bank_application.utils.Converter;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class Account {
    private Long id;
    private Long clientId;
    private BigDecimal amount;
    private Currencies accCode;
    private Integer isMain = 0;

    public Account() {
        amount = new BigDecimal(0);
    }

    @Override
    public String toString() {
        return String.format("%-13s%-10s%5s%10s%20s", clientId, amount, accCode, isMain,
                Converter.autoConvert(amount, accCode, Currencies.RUB));
    }
}
