package tasks.task4;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ConsoleApp {

    private static Pattern pattern = Pattern.compile("\\D", Pattern.CASE_INSENSITIVE);

    public static void printInfo(boolean fl) {
        if (fl) {
            System.out.println("Success");
        } else {
            System.out.println("Error");
        }
    }

    public static boolean goodNumber(String number) {
        return !pattern.matcher(number).find();
    }

    public static String readNumber(Scanner scanner) {
        String phone = scanner.next();
        if (!goodNumber(phone)) {
            System.out.println("Incorrect number");
            return null;
        }
        return phone;
    }

    public static void main(String[] args) {
        PhonesBook phonesBook = new PhonesBook();
        Scanner scanner = new Scanner(System.in);
        String phone;
        String name;
        String surname;
        boolean cycle = true;
        while (cycle) {
            System.out.print("Command: ");
            String command = scanner.next();
            switch (command) {
                case "add":
                    System.out.print("Phone: ");
                    phone = readNumber(scanner);
                    if (phone == null) break;
                    System.out.print("Name : ");
                    name = scanner.next();
                    System.out.print("Surname : ");
                    surname = scanner.next();
                    printInfo(phonesBook.addNumber(phone, name, surname));
                    break;
                case "del":
                    System.out.print("Phone: ");
                    phone = readNumber(scanner);
                    if (phone == null) break;
                    printInfo(phonesBook.deleteNumber(phone));
                    break;
                case "ch_subs":
                    System.out.print("Phone: ");
                    phone = readNumber(scanner);
                    if (phone == null) break;
                    System.out.print("New name : ");
                    name = scanner.next();
                    System.out.print("New surname : ");
                    surname = scanner.next();
                    printInfo(phonesBook.changeSubscriber(phone, name, surname));
                    break;
                case "ch_numb":
                    System.out.print("Old phone: ");
                    phone = readNumber(scanner);
                    if (phone == null) break;
                    System.out.print("New phone: ");
                    String newPhone = scanner.next();
                    printInfo(phonesBook.changeNumber(phone, newPhone));
                    break;
                case "f_subs":
                    System.out.print("Phone: ");
                    phone = readNumber(scanner);
                    if (phone == null) break;
                    System.out.println(phonesBook.findSubscriber(phone));
                    break;
                case "f_numb":
                    System.out.print("Name : ");
                    name = scanner.next();
                    System.out.print("Surname : ");
                    surname = scanner.next();
                    System.out.println(phonesBook.strictCoincidence(name, surname));
                    break;
                case "close":
                    cycle = false;
                    break;
                case "print":
                    System.out.println(phonesBook);
                    break;
                default:
                    System.out.println("Unknown command");
                    break;
            }
        }
    }
}
