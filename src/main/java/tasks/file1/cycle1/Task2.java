package tasks.file1.cycle1;

import java.util.Scanner;

public class Task2 {

    static boolean goodNum(int x) {
        return x % 5 == 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите N до которого будем перебирать: ");
        int N = scanner.nextInt();
        System.out.println("Подходящие числа: ");
        for (int i = 1; i <= N; i++) {
            if (goodNum(i)) {
                System.out.println(i);
            }
        }
    }
}
