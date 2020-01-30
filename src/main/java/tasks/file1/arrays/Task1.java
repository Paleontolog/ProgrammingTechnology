package tasks.file1.arrays;

import java.util.Scanner;
import java.util.function.Predicate;

public class Task1 {

    static Scanner scanner = new Scanner(System.in);

    static void printIf(int[] array, Predicate<Integer> cond) {
        for (int i = 0; i < array.length; i++) {
            if (cond.test(array[i])) {
                System.out.print(array[i] + " ");
            }
        }
    }

    static int[] readArray() {
        System.out.print("Введите количество элементов: ");
        int N = scanner.nextInt();
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = readArray();
        System.out.println("Чётные числа : ");
        printIf(array, x -> x % 2 == 0);
        System.out.println("\nНечётные числа : ");
        printIf(array, x -> x % 2 != 0);
    }
}
