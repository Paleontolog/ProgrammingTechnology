package tasks.file1.condition;

import java.util.Scanner;

public class Task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите 3 числа в строку: ");
        for (int i = 0; i < 3; i++) {
            double x = scanner.nextDouble();
            x = x > 0 ? Math.pow(x, 2) : x;
            System.out.print(x + " ");
        }
    }
}
