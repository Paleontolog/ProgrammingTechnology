package com.bankapp.entities.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "OPERATION", schema = "bank")
public class OperationDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "OPERATION_DATE", nullable = false)
    private Date operationDate;


    @ManyToOne(targetEntity = AccountDAO.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "FROM_ID")
    private AccountDAO fromUser;

    @ManyToOne(targetEntity = AccountDAO.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "TO_ID")
    private AccountDAO toUser;

    @Column(name = "TRANS_AMOUNT", nullable = false)
    private BigDecimal transAmount;

    @Column(name = "BEFORE_TRANSFER", nullable = false)
    private BigDecimal beforeTransfer;

    @Column(name = "AFTER_TRANSFER", nullable = false)
    private BigDecimal afterTransfer;

    public OperationDAO(AccountDAO fromId, AccountDAO toId, Date date, BigDecimal money,
                        BigDecimal beforeTransfer, BigDecimal afterTransfer) {
        this.operationDate = date;
        this.fromUser = fromId;
        this.toUser = toId;
        this.transAmount = money;
        this.beforeTransfer = beforeTransfer;
        this.afterTransfer = afterTransfer;
    }


    @Override
    public String toString() {
        return String.format("%-10s%-15s%20s%20s%20s", id, operationDate,
                transAmount, beforeTransfer, afterTransfer);
    }
}