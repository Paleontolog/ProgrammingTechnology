package com.bankapp.entities.dao;

import com.bankapp.constants.Currencies;
import com.bankapp.utils.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Entity
@Table(name = "ACCOUNT", schema = "bank")
public class AccountDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    @Getter @Setter
    private Long id;

    @JoinColumn(name = "CLIENT_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    @Getter @Setter
    private UserDAO client;


    @Column(name = "AMOUNT", nullable = false)
    @Getter
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACC_CODE", nullable = false)
    @Getter @Setter
    private Currencies accCode;

    @Column(name = "IS_MAIN", nullable = false)
    @Getter @Setter
    private Boolean isMain = false;


    @OneToMany(mappedBy = "fromUser", fetch=FetchType.LAZY)
    @Getter @Setter
    private List<OperationDAO> operationsFrom;

    @OneToMany(mappedBy = "toUser", fetch=FetchType.LAZY)
    @Getter @Setter
    private List<OperationDAO> operationsTo;


    public AccountDAO() {
        amount = new BigDecimal(0);
    }


    public BigDecimal addMoney(BigDecimal value) {
        BigDecimal money = this.amount.add(value);
        this.amount = money;
        return money;
    }

    public BigDecimal withdrawMoney(BigDecimal value) {
        BigDecimal money = this.amount.subtract(value);
        this.amount = money;
        return money;
    }


    public AccountDAO(AccountDAO account) {
        this(account.id, account.client, new BigDecimal(0),
                account.accCode, account.isMain, null, null);
    }

    public AccountDAO(UserDAO client, BigDecimal amount, Currencies accCode) {
        this(null, client, amount, accCode, false,
                null, null);
    }

    @Override
    public String toString() {
        return String.format("%-10s%5s%10s%20s", amount, accCode, isMain,
                Converter.autoConvert(amount, accCode, Currencies.RUB));
    }
}