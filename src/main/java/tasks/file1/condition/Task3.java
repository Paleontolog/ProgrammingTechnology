package tasks.file1.condition;

import java.util.Scanner;

public class Task3 {

    static double distance(double x, double y) {
        return Math.sqrt(x * x + y * y);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            double maxDist = 0;
            String pointWithMaxDist = null;
            for (int i = 0; i < 2; i++) {
                System.out.print("Введите буквенное обозначение точки: ");
                String point = scanner.next();
                System.out.print(point + " (x, y): ");
                double x = scanner.nextDouble();
                double y = scanner.nextDouble();
                double dist = distance(x, y);
                if (dist > maxDist) {
                    maxDist = dist;
                    pointWithMaxDist = point;
                }
            }
            System.out.println("Максимально удалённая точка : " + pointWithMaxDist + " расстояние :" + maxDist);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
