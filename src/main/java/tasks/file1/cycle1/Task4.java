package tasks.file1.cycle1;

import java.util.Scanner;

public class Task4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите N: ");
        int N = scanner.nextInt();
        int predFib = 0;
        int curFib = 1;
        while (curFib < N) {
            System.out.print(curFib + " ");
            int temp = predFib;
            predFib = curFib;
            curFib += temp;
        }
    }
}
