package tasks.bank_application.model;

import tasks.bank_application.constants.OperationResults;
import tasks.bank_application.database.DatabaseAccessor;
import tasks.bank_application.entities.Account;
import tasks.bank_application.entities.Operation;
import tasks.bank_application.entities.User;
import tasks.bank_application.utils.Converter;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class BankServiceImpl implements BankService {
    private DatabaseAccessor databaseAccessor;

    public BankServiceImpl(DatabaseAccessor databaseAccessor) {
        this.databaseAccessor = databaseAccessor;
    }

    private void logChange(Account account, BigDecimal money,
                           BigDecimal moneyBefore, BigDecimal moneyAfter) {
        Operation operation = new Operation(null,
                new Date(Calendar.getInstance().getTime().getTime()),
                account.getId(),
                account.getId(),
                money,
                moneyBefore,
                moneyAfter);
        databaseAccessor.logOperation(operation);
    }

    private void logTransfer(Account accountFrom, Account accountTo, BigDecimal money,
                             BigDecimal moneyBefore, BigDecimal moneyAfter) {
        Operation operation = new Operation(null,
                new Date(Calendar.getInstance().getTime().getTime()),
                accountFrom.getId(),
                accountTo.getId(),
                money,
                moneyBefore,
                moneyAfter);
        databaseAccessor.logOperation(operation);
    }

    @Override
    public boolean existUser(String name) {
        return databaseAccessor.getUser(name, "") != null;
    }

    @Override
    public OperationResults registration(User user) {
        if (existUser(user.getLogin())) {
            return OperationResults.USER_ALREADY_EXISTS;
        }
        if (databaseAccessor.createUser(user) == 0) {
            return OperationResults.USER_CREATE_ERROR;
        }
        return OperationResults.SUCCESS;
    }

    @Override
    public OperationResults createAccount(Account account) {
        if (databaseAccessor.getUserAccounts(account.getClientId()).isEmpty()) {
            account.setIsMain(1);
        }
        if (databaseAccessor.createAccount(account) == 0) {
            return OperationResults.ACCOUNT_CREATE_ERROR;
        }
        return OperationResults.SUCCESS;
    }


    @Override
    public BigDecimal addMoney(Account account, BigDecimal money, boolean isLogged) {
        Account currentAccount = databaseAccessor.getAccount(account.getId());
        BigDecimal newMoney = currentAccount.getAmount().add(money);
        if (databaseAccessor.setMoney(account.getId(), newMoney) == 0) {
            newMoney = null;
        }
        if (isLogged) {
            logChange(account, money, currentAccount.getAmount(), newMoney);
        }
        return newMoney;
    }

    @Override
    public BigDecimal addMoney(Account account, BigDecimal money) {
        return addMoney(account, money, true);
    }

    @Override
    public BigDecimal withdrawMoney(Account account, BigDecimal money, boolean isLogged) {
        Account currentAccount = databaseAccessor.getAccount(account.getId());
        BigDecimal newMoney = currentAccount.getAmount().subtract(money);
        if (databaseAccessor.setMoney(account.getId(), newMoney) == 0) {
            newMoney = null;
        }
        if (isLogged) {
            logChange(account, money, currentAccount.getAmount(), newMoney);
        }
        return newMoney;
    }

    @Override
    public BigDecimal withdrawMoney(Account account, BigDecimal money) {
        return withdrawMoney(account, money, true);
    }


    @Override
    public OperationResults transfer(Account account, String phone, BigDecimal money) {
        User userTo = databaseAccessor.getUser("", phone);
        if (userTo == null) {
            return OperationResults.USER_DOES_NOT_EXISTS;
        }
        List<Account> accountsList = databaseAccessor.getUserAccounts(userTo.getId());
        if (accountsList.size() == 0) {
            return OperationResults.ACCOUNT_DOES_NOT_EXIST;
        }
        Account accountTo = accountsList.stream()
                .filter(x -> x.getIsMain() == 1)
                .collect(Collectors.toList())
                .get(0);

        BigDecimal newMoney = withdrawMoney(account, money, false);

        if (newMoney == null) {
            return OperationResults.WITHDRAW_MONEY_ERROR;
        }

        BigDecimal convertMoney =
                Converter.autoConvert(money, account.getAccCode(), accountTo.getAccCode());

        if (addMoney(accountTo, convertMoney, false) == null) {
            return OperationResults.ADD_MONEY_ERROR;
        }

        logTransfer(account, accountTo, money, account.getAmount(), newMoney);
        return OperationResults.SUCCESS;
    }

    @Override
    public void destroyConnection() throws SQLException {
        databaseAccessor.destroyConnection();
    }

    @Override
    public User login(String name, String password) {
        User user = databaseAccessor.getUser(name, "");
        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }
        return user;
    }

    @Override
    public List<Account> getUserAccounts(User user) {
        return databaseAccessor.getUserAccounts(user.getId());
    }
}
