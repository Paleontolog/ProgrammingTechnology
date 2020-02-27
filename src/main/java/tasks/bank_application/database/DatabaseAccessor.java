package tasks.bank_application.database;

import tasks.bank_application.entities.Account;
import tasks.bank_application.entities.Operation;
import tasks.bank_application.entities.User;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface DatabaseAccessor {
    int createUser(User user);
    int createAccount(Account account);
    Account getAccount(Long accountId);
    User getUser(String login, String phone);
    int setMoney(Long accountId, BigDecimal money);
    List<Account> getUserAccounts(Long userId);
    int logOperation(Operation operation);
    List<Operation> getUserOperation(Long accountID);
    void destroyConnection() throws SQLException;


}
