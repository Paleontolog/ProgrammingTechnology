package com.bankapp.constants;
import java.math.BigDecimal;

public enum Currencies {
    RUB(new BigDecimal(1.0)), EUR(new BigDecimal(68.78)), USD(new BigDecimal(64.3));
    BigDecimal rubValue;
    Currencies(BigDecimal val) {
        rubValue = val;
    }
    public BigDecimal getRubValue() {
        return rubValue;
    }
}
