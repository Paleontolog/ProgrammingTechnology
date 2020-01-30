package tasks.file1.condition;

import java.util.Scanner;

public class Task2 {

    static boolean between(int a, int x, int y) {
        return a >= x && a <= y;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Введите стаж и зарплату: ");
            int stage = Integer.parseInt(scanner.next());
            double money = Double.parseDouble(scanner.next());
            double bounce = 0;
            if (between(stage, 2, 4)) {
                bounce = 0.02;
                money += money * bounce;
            } else if (between(stage, 5, 9)) {
                bounce = 0.05;
                money += money * bounce;
            }
            System.out.println("Надбавка: " + bounce);
            System.out.println("Сумма к выплате: " + money);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
