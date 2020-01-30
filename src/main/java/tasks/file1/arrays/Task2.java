package tasks.file1.arrays;

public class Task2 {

    public static void main(String[] args) {

        int[] array = Task1.readArray();
        System.out.println("Делятся на 3 или на 9: ");
        Task1.printIf(array, x -> x % 3 == 0);
    }
}
