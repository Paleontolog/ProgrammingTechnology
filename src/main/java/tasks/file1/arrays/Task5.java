package tasks.file1.arrays;

public class Task5 {

    static boolean isSimple(int x) {
        for (int i = 2; i <= Math.sqrt(x); i++) {
            if (x % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] array = Task1.readArray();
        System.out.println("Простые числа: ");
        Task1.printIf(array, Task5::isSimple);
    }
}
