package com.bankapp.service;

import com.bankapp.constants.OperationResults;
import com.bankapp.entities.dao.AccountDAO;
import com.bankapp.entities.dao.OperationDAO;
import com.bankapp.entities.dao.UserDAO;
import com.bankapp.entities.dto.AccountCreateRequest;
import com.bankapp.entities.dto.AccountDTO;
import com.bankapp.entities.dto.OperationDTO;
import com.bankapp.entities.dto.UserDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public interface BankService {
    OperationResults createAccount(AccountCreateRequest account);
    AccountDAO addMoney(AccountDAO account, BigDecimal money, boolean isLogged);
    AccountDAO addMoney(AccountDAO account, BigDecimal money);
    AccountDAO withdrawMoney(AccountDAO account, BigDecimal money);
    AccountDAO withdrawMoney(AccountDAO account, BigDecimal money, boolean isLogged);
    OperationResults transfer(AccountDAO account, String phone, BigDecimal money);
    List<AccountDAO> getUserAccounts(String name);
    List<OperationDTO> getUserOperationStory(String name);
    Optional<AccountDAO> getAccountById(Long id);

    List<OperationDTO> getAllHistory();
    List<AccountDTO> getAllAccounts();
    List<UserDTO> getAllUser();
}