package tasks.file1.condition;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Task4 {
    static boolean isRectangular(List<Double> points) {
        return Math.pow(points.get(0), 2) +
               Math.pow(points.get(1), 2) ==
               Math.pow(points.get(2), 2);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Введите длины сторон треугольника: ");
            List<Double> points = Arrays.stream(scanner.nextLine().split(" "))
                    .map(Double::parseDouble)
                    .sorted(Double::compareTo)
                    .collect(Collectors.toList());
            if (isRectangular(points)) {
                System.out.println("Треугольник прямоугольный");
            } else {
                System.out.println("Треугольник не является прямоугольным");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
