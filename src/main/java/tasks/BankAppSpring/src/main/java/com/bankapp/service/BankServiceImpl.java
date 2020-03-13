package com.bankapp.service;

import com.bankapp.constants.OperationResults;
import com.bankapp.database.AccountRepository;
import com.bankapp.database.OperationRepository;
import com.bankapp.database.UserRepository;
import com.bankapp.entities.dao.AccountDAO;
import com.bankapp.entities.dao.OperationDAO;
import com.bankapp.entities.dao.UserDAO;
import com.bankapp.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
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

    @Override
    public OperationResults registration(UserDAO user) {
        if (userRepository.findByLoginOrPhone(user.getLogin(), user.getPhone()) != null) {
            return OperationResults.USER_ALREADY_EXISTS;
        }
        userRepository.save(user);
        return OperationResults.SUCCESS;
    }

    @Override
    public OperationResults createAccount(AccountDAO account) {
        if (accountRepository.findAllByClientId(account.getClient().getId()).isEmpty()) {
            account.setIsMain(true);
        }
        accountRepository.save(account);
        return OperationResults.SUCCESS;
    }

    @Override
    public AccountDAO addMoney(AccountDAO account, BigDecimal money, boolean isLogged) {
        AccountDAO newAccount = new AccountDAO(account);
        newAccount.setAmount(account.getAmount().add(money));
        newAccount = accountRepository.save(newAccount);
        if (isLogged) {
            OperationDAO operationDAO = new OperationDAO(account.getId(),
                    newAccount.getId(), new Date(),
                    money, account.getAmount(), newAccount.getAmount());
            operationRepository.save(operationDAO);
        }
        return newAccount;
    }

    @Override
    public AccountDAO addMoney(AccountDAO account, BigDecimal money) {
        return addMoney(account, money, true);
    }

    @Override
    public AccountDAO withdrawMoney(AccountDAO account, BigDecimal money, boolean isLogged) {
        AccountDAO newAccount = new AccountDAO(account);
        newAccount.setAmount(account.getAmount().subtract(money));
        newAccount = accountRepository.save(newAccount);
        if (isLogged) {
            OperationDAO operationDAO = new OperationDAO(account.getId(),
                    newAccount.getId(), new Date(),
                    money, account.getAmount(), newAccount.getAmount());
            operationRepository.save(operationDAO);
        }
        return newAccount;
    }

    @Override
    public OperationResults transfer(AccountDAO account, String phone, BigDecimal money) {
        UserDAO userTo = userRepository.findByLoginOrPhone("", phone);
        if (userTo == null) {
            return OperationResults.USER_DOES_NOT_EXISTS;
        }
        List<AccountDAO> accountsList = accountRepository.findAllByClientId(userTo.getId());
        if (accountsList.isEmpty()) {
            return OperationResults.ACCOUNT_DOES_NOT_EXIST;
        }

        AccountDAO accountTo = accountsList.stream()
                .filter(AccountDAO::getIsMain)
                .collect(Collectors.toList())
                .get(0);

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

        OperationDAO operationDAO = new OperationDAO(account.getId(),
                accountTo.getId(), new Date(),
                money, account.getAmount(), withdrawMoneyAcc.getAmount());

        operationRepository.save(operationDAO);
        return OperationResults.SUCCESS;
    }

    @Override
    public AccountDAO withdrawMoney(AccountDAO account, BigDecimal money) {
        return withdrawMoney(account, money, true);
    }

    @Override
    public List<AccountDAO> getUserAccounts(Long id) {
        return accountRepository.findAllByClientId(id);
    }

    @Override
    public List<OperationDAO> getUserOperationStory(AccountDAO account) {
        return operationRepository.findAllByFromUserIdAndToUserId(account.getId(), account.getId());
    }

    @Override
    public Optional<AccountDAO> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

}
