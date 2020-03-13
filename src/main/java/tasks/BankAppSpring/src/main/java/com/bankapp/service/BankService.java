package com.bankapp.service;

import com.bankapp.constants.OperationResults;
import com.bankapp.entities.dao.AccountDAO;
import com.bankapp.entities.dao.OperationDAO;
import com.bankapp.entities.dao.UserDAO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public interface BankService {

    OperationResults registration(UserDAO user);
    OperationResults createAccount(AccountDAO account);

    AccountDAO addMoney(AccountDAO account, BigDecimal money, boolean isLogged);
    AccountDAO addMoney(AccountDAO account, BigDecimal money);
    AccountDAO withdrawMoney(AccountDAO account, BigDecimal money);
    AccountDAO withdrawMoney(AccountDAO account, BigDecimal money, boolean isLogged);
    OperationResults transfer(AccountDAO account, String phone, BigDecimal money);
    List<AccountDAO> getUserAccounts(Long id);
    List<OperationDAO> getUserOperationStory(AccountDAO account);
    Optional<AccountDAO> getAccountById(Long id);
}