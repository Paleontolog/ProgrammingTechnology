package com.bankapp.entities.dao;
import com.bankapp.constants.Currencies;
import com.bankapp.utils.Converter;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@Data
@Entity
@Table(name = "ACCOUNT", schema = "bank")
public class AccountDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @JoinColumn(name = "CLIENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserDAO client;


    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACC_CODE", nullable = false)
    private Currencies accCode;

    @Column(name = "IS_MAIN", nullable = false)
    private Boolean isMain = false;

    public AccountDAO() {
        amount = new BigDecimal(0);
    }

    public AccountDAO(AccountDAO account) {
        this(account.id, account.client, account.amount,
                account.accCode, account.isMain);
    }


    @Override
    public String toString() {
        return String.format("%-13s%-10s%5s%10s%20s", client.getId(), amount, accCode, isMain,
                Converter.autoConvert(amount, accCode, Currencies.RUB));
    }
}