package tasks.file1.cycle1;

import java.util.Scanner;

public class Task1 {

    static int sumNum(int x) {
        int sum  = 0;
        do {
            sum += x % 10;
            x = x / 10;
        } while (x != 0);
        return sum;
    }

    static boolean goodNum(int x) {
        return x % 5 != 0 && x % 3 == 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите N до которого будем перебирать: ");
        int N = scanner.nextInt();
        System.out.println("Подходящие числа: ");
        for (int i = 0; i < N; i++) {
            if (goodNum(i) && goodNum(sumNum(i))) {
                System.out.println(i);
            }
        }
    }
}
