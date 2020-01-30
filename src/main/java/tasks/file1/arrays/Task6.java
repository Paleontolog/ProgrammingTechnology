package tasks.file1.arrays;

public class Task6 {

    static int numLen(int x) {
        long p = 10;
        for (int i = 1; i < 19; i++) {
            if (x < p)
                return i;
            p = 10 * p;
        }
        return 19;
    }

    static int sumNum(int x) {
        int sum  = 0;
        do {
            sum += x % 10;
            x = x / 10;
        } while (x != 0);
        return sum;
    }

    static boolean isHappy(int x) {
        int len = numLen(Math.abs(x));
        int half = len / 2;
        return sumNum((int) (x % Math.pow(10, half))) ==
                sumNum((int) (x / Math.pow(10, half + (len % 2 != 0 ? 1 : 0))));
    }

    public static void main(String[] args) {
        int[] array = Task1.readArray();
        System.out.println("Счастливые числа: ");
        Task1.printIf(array, Task6::isHappy);
    }
}
