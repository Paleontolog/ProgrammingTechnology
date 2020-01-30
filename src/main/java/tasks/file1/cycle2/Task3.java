package tasks.file1.cycle2;

import java.util.Scanner;

public class Task3 {

    static double pow(double A, int N) {
        double res = A;
        for (int i = 1; i < N; i++) {
            res *= A;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите А и N: ");
        double A = scanner.nextDouble();
        int N = scanner.nextInt();
        System.out.println("A ^ N = " + pow(A, N));
    }
}
