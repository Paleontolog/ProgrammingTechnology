package tasks.file1.cycle2;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите А и В: ");
        int A = scanner.nextInt();
        int B = scanner.nextInt();
        System.out.println("Подходящие числа: ");
        for (int i = B - 1; i > A; i--) {
            System.out.print(i + " ");
        }
        System.out.print("\nКоличество чисел: " + (B - A - 1));
    }
}
