package com.bankapp.service;

import com.bankapp.constants.OperationResults;
import com.bankapp.database.AccountRepository;
import com.bankapp.database.OperationRepository;
import com.bankapp.database.UserRepository;
import com.bankapp.entities.dao.AccountDAO;
import com.bankapp.entities.dao.OperationDAO;
import com.bankapp.entities.dao.UserDAO;
import com.bankapp.entities.dto.AccountCreateRequest;
import com.bankapp.entities.dto.AccountDTO;
import com.bankapp.entities.dto.OperationDTO;
import com.bankapp.entities.dto.UserDTO;
import com.bankapp.mapper.EntityMapper;
import com.bankapp.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.ManyToMany;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BankServiceImpl implements BankService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private EntityMapper mapper;


    @Override
    public OperationResults createAccount(AccountCreateRequest accountCreateRequest) {
        Optional<UserDAO> user = userRepository.findByLogin(accountCreateRequest.getClientName());
        if (!user.isPresent()) {
            return OperationResults.USER_DOES_NOT_EXISTS;
        }
        AccountDAO account = new AccountDAO(user.get(),
                                            accountCreateRequest.getAmount(),
                                            accountCreateRequest.getAccCode());
        if (accountRepository.findAllByClientId(user.get().getId()).isEmpty()) {
            account.setIsMain(true);
        }
        accountRepository.save(account);
        return OperationResults.SUCCESS;
    }

    @Override
    public AccountDAO addMoney(AccountDAO account, BigDecimal money, boolean isLogged) {
       // AccountDAO newAccount = new AccountDAO(account);
        BigDecimal oldAmount = account.getAmount();
        account.addMoney(money);
        accountRepository.save(account);
        if (isLogged) {
            OperationDAO operationDAO = new OperationDAO(account,
                    account, new Date(),
                    money, oldAmount, account.getAmount());
            operationRepository.save(operationDAO);
        }
        return account;
    }

    @Override
    public AccountDAO addMoney(AccountDAO account, BigDecimal money) {
        return addMoney(account, money, true);
    }

    @Override
    public AccountDAO withdrawMoney(AccountDAO account, BigDecimal money, boolean isLogged) {
        BigDecimal oldAmount = account.getAmount();
        //AccountDAO newAccount = new AccountDAO(account);
        account.withdrawMoney(money);
        accountRepository.save(account);
        if (isLogged) {
            OperationDAO operationDAO = new OperationDAO(account,
                    account, new Date(),
                    money, oldAmount, account.getAmount());
            operationRepository.save(operationDAO);
        }
        return account;
    }

    @Override
    public OperationResults transfer(AccountDAO account, String phone, BigDecimal money) {
        Optional<UserDAO> userTo = userRepository.findByLoginOrPhone("", phone);
        if (!userTo.isPresent()) {
            return OperationResults.USER_DOES_NOT_EXISTS;
        }
        List<AccountDAO> accountsList = accountRepository.findAllByClientId(userTo.get().getId());
        if (accountsList.isEmpty()) {
            return OperationResults.ACCOUNT_DOES_NOT_EXIST;
        }

        AccountDAO accountTo = accountsList.stream()
                .filter(AccountDAO::getIsMain)
                .collect(Collectors.toList())
                .get(0);

        BigDecimal oldAmount = account.getAmount();
        AccountDAO withdrawMoneyAcc = withdrawMoney(account, money, false);

        if (withdrawMoneyAcc == null) {
            return OperationResults.WITHDRAW_MONEY_ERROR;
        }

        BigDecimal convertMoney =
                Converter.autoConvert(money, account.getAccCode(), accountTo.getAccCode());

        AccountDAO addMoneyAcc = addMoney(accountTo, convertMoney, false);

        if (addMoneyAcc == null) {
            return OperationResults.ADD_MONEY_ERROR;
        }

        OperationDAO operationDAO = new OperationDAO(account,
                accountTo, new Date(),
                money, oldAmount, withdrawMoneyAcc.getAmount());

        operationRepository.save(operationDAO);
        return OperationResults.SUCCESS;
    }

    @Override
    public AccountDAO withdrawMoney(AccountDAO account, BigDecimal money) {
        return withdrawMoney(account, money, true);
    }

    @Override
    public List<AccountDAO> getUserAccounts(String name) {
        return accountRepository.findAllByClient_Login(name);
    }

    @Override
    public List<OperationDTO> getUserOperationStory(String name) {
        Optional<UserDAO> user = userRepository.findByLogin(name);
        return user.map(userDAO -> operationRepository.findAllByFromUserIdOrToUserId(userDAO.getId(), userDAO.getId())
                .stream().map(mapper::operationDaoToDto)
                .collect(Collectors.toList())).orElse(Collections.emptyList());
    }

    @Override
    public Optional<AccountDAO> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public List<OperationDTO> getAllHistory() {
        return operationRepository.findAll()
                .stream()
                .map(mapper::operationDaoToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(mapper::accountDaoToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(mapper::userDaoToDto)
                .collect(Collectors.toList());
    }

}
