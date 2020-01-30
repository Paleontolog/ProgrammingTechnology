package tasks.file1.cycle2;

import java.util.Scanner;

public class Task4 {

    private static double eps = 1e-5;

    static boolean isPowOfA(double x, double A) {
        double res = Math.log(x) / Math.log(A);
        return Math.abs(Math.round(res) - res) < eps;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите А и N: ");
        double A = scanner.nextDouble();
        int N = scanner.nextInt();
        System.out.println("Числа, являющиеся степенью числа : " + A);
        for (int i = 1; i <= N; i++) {
            if (isPowOfA(i, A)) {
                System.out.print(i + " ");
            }
        }
    }
}
