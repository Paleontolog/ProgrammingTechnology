package tasks.file1.arrays;

import java.math.BigInteger;
import java.util.Scanner;

public class Task4 {

    static BigInteger getDCG(BigInteger[] array) {
        BigInteger res = array[0];
        for (int i = 1; i < array.length; i++) {
            res = res.gcd(array[i]);
        }
        return res;
    }

    static BigInteger getLCM(BigInteger[] array) {
        BigInteger res = array[0];
        for (int i = 1; i < array.length; i++) {
            res = res.divide(res.gcd(array[i])).multiply(array[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов: ");
        int N = scanner.nextInt();
        BigInteger[] array = new BigInteger[N];
        for (int i = 0; i < N; i++) {
            array[i] = scanner.nextBigInteger();
        }
        BigInteger dcg = getDCG(array);
        System.out.println("НОД: " + dcg);
        BigInteger lcm = getLCM(array);
        System.out.println("НОК: " + lcm);
    }
}
