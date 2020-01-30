package tasks.file1.arrays;

public class Task3 {
    public static void main(String[] args) {
        int[] array = Task1.readArray();
        System.out.println("Делятся на 5 или на 10: ");
        Task1.printIf(array, x -> x % 5 == 0);
    }
}
