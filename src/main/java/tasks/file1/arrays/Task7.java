package tasks.file1.arrays;

import java.util.Scanner;

public class Task7 {
    static String[] nums = {
            "Ноль", "Один", "Два", "Три",
            "Четыре", "Пять", "Шесть", "Семь",
            "Восемь", "Девять"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число: ");
        int n = scanner.nextInt();
        if (n >= 0 && n <= 9) {
            System.out.println("Введённая цифра: " + nums[n]);
        } else {
            System.out.println("Цифра вне диапазона");
        }
    }
}
