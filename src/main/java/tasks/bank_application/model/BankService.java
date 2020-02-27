package tasks.bank_application.model;

import tasks.bank_application.constants.OperationResults;
import tasks.bank_application.entities.Account;
import tasks.bank_application.entities.Operation;
import tasks.bank_application.entities.User;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface BankService {
    boolean existUser(String name);
    OperationResults registration(User user);
    OperationResults createAccount(Account account);
    BigDecimal addMoney(Account account, BigDecimal money, boolean isLogged);
    BigDecimal addMoney(Account account, BigDecimal money);
    BigDecimal withdrawMoney(Account account, BigDecimal money);
    BigDecimal withdrawMoney(Account account, BigDecimal money, boolean isLogged);
    OperationResults transfer(Account account, String phone, BigDecimal money);
    User login(String name, String password);
    List<Account> getUserAccounts(User user);
    List<Operation> getUserOperationStory(Account account);
    void destroyConnection() throws SQLException;
}

