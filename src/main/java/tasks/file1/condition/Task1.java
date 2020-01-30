package tasks.file1.condition;

import java.util.Scanner;

public class Task1 {
    static int sumOfSquares(int x, int y) {
        return x * x + y * y;
    }

    static int squareOfSum(int x, int y) {
        int t = x + y;
        return t * t;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Введите 2 числа");
            int x = Integer.parseInt(scanner.next());
            int y = Integer.parseInt(scanner.next());

            if (sumOfSquares(x, y) <= Task1.squareOfSum(x, y)) {
                System.out.println("Сумма квадратов меньше");
            } else {
                System.out.println("Квадрат суммы меньше");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
