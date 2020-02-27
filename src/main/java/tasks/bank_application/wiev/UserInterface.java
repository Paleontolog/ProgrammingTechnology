package tasks.bank_application.wiev;

import org.apache.commons.codec.digest.DigestUtils;
import tasks.bank_application.constants.Currencies;
import tasks.bank_application.constants.OperationResults;
import tasks.bank_application.database.DatabaseAccessorJDBC;
import tasks.bank_application.database.DatabaseConnector;
import tasks.bank_application.entities.Account;
import tasks.bank_application.entities.Operation;
import tasks.bank_application.entities.User;
import tasks.bank_application.model.BankService;
import tasks.bank_application.model.BankServiceImpl;
import tasks.bank_application.utils.Converter;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private BankService bankService;
    private Scanner scanner;
    private User currentUser = null;

    public UserInterface(Connection connection) throws FileNotFoundException {
        bankService = new BankServiceImpl(new DatabaseAccessorJDBC(connection));
        scanner = new Scanner(System.in);
    }

    private void destroyConnection() throws SQLException {
        bankService.destroyConnection();
    }

    public void createUser() {
        User user = new User();
        System.out.print("Введите логин: ");
        String login = scanner.next();

        if (bankService.existUser(login)) {
            System.out.println("Пользователь с таким именем уже существует");
            return;
        }
        user.setLogin(login);
        System.out.print("Введите пароль: ");
        String pass = DigestUtils.sha256Hex(scanner.next());
        user.setPassword(pass);

        System.out.print("Введите адрес: ");
        user.setAddress(scanner.next());

        System.out.print("Введите телефон: ");
        user.setPhone(scanner.next());


        OperationResults result = bankService.registration(user);
        if (result == OperationResults.SUCCESS) {
            System.out.println("Регистрация успешна");
        } else if (result == OperationResults.USER_ALREADY_EXISTS) {
            System.out.println("Пользователь уже существует");
        } else if (result == OperationResults.USER_CREATE_ERROR) {
            System.out.println("Ошибка регистрации");
        }
    }

    public void login() {
        System.out.print("Введите логин: ");
        String login = scanner.next();
        System.out.print("Введите пароль: ");
        String pass = DigestUtils.sha256Hex(scanner.next());
        currentUser = bankService.login(login, pass);
        if (currentUser == null) {
            System.out.println("Неверный логин и/или пароль");
        } else {
            System.out.println("Авторизация успешна");
        }
    }

    public void createAccount() {
        Account account = new Account();
        System.out.print("Введите валюту (USD, RUB, EUR): ");
        String money = scanner.next();
        account.setAccCode(Currencies.valueOf(money));
        account.setClientId(currentUser.getId());
        OperationResults result = bankService.createAccount(account);
        if (result == OperationResults.SUCCESS) {
            System.out.println("Счёт успешно создан");
        } else if (result == OperationResults.ACCOUNT_CREATE_ERROR) {
            System.out.println("Ошибка создания счёта");
        }
    }

    private void printUserAccounts(List<Account> accounts) {
        System.out.println("№ " + " ID клиента " + " Сумма на счету " + " Валюта " + " Счёт основной " +
                " Рублёвый эквивалент ");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(i + "      " + accounts.get(i));
        }
    }

    private Account chooseAccount() {
        System.out.print("Ваши счета: ");
        List<Account> accountList = bankService.getUserAccounts(currentUser);
        printUserAccounts(accountList);
        System.out.print("Выберите счёт: ");
        int n = scanner.nextInt();
        if (n > accountList.size() - 1) {
            System.out.println("Выбран неверный счёт");
            return null;
        }
        return accountList.get(n);
    }

    public void addMoney() {

        Account account = chooseAccount();
        if (account == null) {
            return;
        }

        System.out.print("Введите валюту (USD, RUB, EUR): ");
        Currencies currency = Currencies.valueOf(scanner.next());

        System.out.print("Введите сумму: ");
        BigDecimal money = scanner.nextBigDecimal();

        money = Converter.autoConvert(money, currency, account.getAccCode());

        if (bankService.addMoney(account, money) == null) {
            System.out.println("Ошибка, попытка пополнения счёта неудачна");
        } else {
            System.out.println("Счёт пополнен");
        }
    }


    public void transfer() {

        Account account = chooseAccount();
        if (account == null) {
            return;
        }
        System.out.print("Введите сумму: ");
        BigDecimal money = scanner.nextBigDecimal();

        System.out.print("Введите номер для перевода: ");
        String phone = scanner.next();

        OperationResults result = bankService.transfer(account, phone, money);
        if (result == OperationResults.SUCCESS) {
            System.out.println("Перевод успешно произведён");
        } else if (result == OperationResults.USER_DOES_NOT_EXISTS) {
            System.out.println("Пользователя с таким телефоном не существует");
        } else if (result == OperationResults.ACCOUNT_DOES_NOT_EXIST) {
            System.out.println("У пользователя нет счетов");
        } else if (result == OperationResults.WITHDRAW_MONEY_ERROR) {
            System.out.println("Ошибка при снятии денег с вашего счёта");
        } else {
            System.out.println("Ошибка при переводе денег на счёт");
        }
    }


    private void showOperation() {
        Account account = chooseAccount();
        if (account == null) {
            return;
        }

        List<Operation> operations = bankService.getUserOperationStory(account);

        System.out.println("№ " + " ID операции " + " Дата операции " + " Номер счета перевода " +
                " Номер счета получателя " + " Сумма перевода " + " На счету до поревода " +
                " На счету после перевода ");

        for (int i = 0; i < operations.size(); i++) {
            System.out.println(i + "      " + operations.get(i));
        }
    }


    public void mainLoop() throws SQLException {
        boolean close = false;
        while (!close) {
            System.out.print("Введите команду (help помощь): ");
            String command = scanner.next();
            switch (command) {
                case "registration":
                    createUser();
                    break;
                case "login":
                    login();
                    break;
                case "q":
                    close = true;
                    break;
                case "help":
                    System.out.println("Список команд: ");
                    System.out.println("q - выход");
                    System.out.println("help - помощь");
                    if (currentUser == null) {
                        System.out.println("registration - зарегистрировать нового пользователя");
                        System.out.println("login - войти в систему");
                    } else {
                        System.out.println("cr_acc - создать аккаунт");
                        System.out.println("print_accs - вывести все аккаунты пользователя");
                        System.out.println("add_money - внести деньги на счёт");
                        System.out.println("transfer - перевести деньги");
                        System.out.println("delog - разлогиниться");
                        System.out.println("sh_op - показать все операции по конкретному счёту");
                    }
                    break;
                default:
                    if (currentUser != null) {
                        switch (command) {
                            case "cr_acc":
                                createAccount();
                                break;
                            case "print_accs":
                                printUserAccounts(bankService.getUserAccounts(currentUser));
                                break;
                            case "add_money":
                                addMoney();
                                break;
                            case "transfer":
                                transfer();
                                break;
                            case  "sh_op":
                                showOperation();
                                break;
                            case "delog":
                                currentUser = null;
                                System.out.println("Вы вышли из системы");
                                break;
                            default:
                                System.out.println("Неизвестная команда");
                                break;
                        }
                    } else {
                        System.out.println("Неизвестная команда");
                    }
                    break;
            }
        }
        destroyConnection();
    }

    public static void main(String[] args) {
        try {
            UserInterface userInterface = new UserInterface(DatabaseConnector.getConnection());
            userInterface.mainLoop();
        } catch (FileNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
