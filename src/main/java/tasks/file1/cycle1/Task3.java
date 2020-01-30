package tasks.file1.cycle1;

import java.util.Scanner;

public class Task3 {

    // Можно и циклом, но для двойки не имеет смысла

    static boolean isPowOfTwo(int x) {
        return x != 0 && (x & x - 1) == 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите N: ");
        int N = scanner.nextInt();
        if (isPowOfTwo(N)) {
            System.out.println("Число " + N + " является степенью двойки");
        } else {
            System.out.println("Число " + N + " не степень двойки");
        }
    }
}
