package tasks.file1.condition;

import java.util.Scanner;

public class Task6 {
    static String[] months = {
            "Январь", "Февраль", "Март", "Апрель",
            "Май", "Июнь", "Июль", "Август",
            "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Введите номер месяца: ");
            int month = scanner.nextInt();
            if (month <= 12 && month >= 1) {
                System.out.println("Вы выбрали: " + months[month - 1]);
            } else {
                System.out.println("К сожалению такого месяца пока нет");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
