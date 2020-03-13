package tasks.bank_application.utils;

import tasks.bank_application.constants.Currencies;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Converter {
    public static BigDecimal usdToRub(BigDecimal usd) {
        return usd.multiply(Currencies.USD.getRubValue()).round(new MathContext(2));
    }

    public static BigDecimal eurToRub(BigDecimal eur) {
        return eur.multiply(Currencies.EUR.getRubValue()).round(new MathContext(2));
    }

    public static BigDecimal rubToEur(BigDecimal rub) {
        return rub.divide(Currencies.EUR.getRubValue(), 2, RoundingMode.DOWN);
    }

    public static BigDecimal rubToUsd(BigDecimal rub) {
        return rub.divide(Currencies.USD.getRubValue(), 2, RoundingMode.DOWN);
    }

    public static BigDecimal eurToUsd(BigDecimal eur) {
        return rubToUsd(eurToRub(eur));
    }

    public static BigDecimal usdToEur(BigDecimal usd) {
        return rubToEur(usdToRub(usd));
    }

    public static BigDecimal autoConvert(BigDecimal money, Currencies from, Currencies to) {
        if (from == to) return money;

        if (from == Currencies.EUR) {
            money = eurToRub(money);
        } else if (from == Currencies.USD) {
            money = usdToRub(money);
        }

        if (to == Currencies.EUR) {
            money = rubToEur(money);
        } else if (to == Currencies.USD) {
            money = rubToUsd(money);
        }

        return money;
    }
}
