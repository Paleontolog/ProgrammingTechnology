package tasks.file3;

import java.time.LocalDate;

public class Worker {
    private String surname;
    private String position;
    private static double salaryPercent = 1;
    private double salary;
    private int year;

    public Worker() {
        surname = "Хорус";
        position = "Магистр Войны";
        salary = 0;
        year = 30000;
    }

    public Worker(String surname, String position, double salary, int year) {
        this.surname = surname;
        this.position = position;
        this.salary = salary;
        this.year = year;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary * salaryPercent;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "surname='" + surname + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary * salaryPercent +
                ", year=" + year +
                '}';
    }

    public void increaseSalaryAllWorker() {
        salaryPercent *= 1.15;
    }

    public int workerAge() {
        return LocalDate.now().getYear() - year;
    }

    public static void main(String[] args) {
        Worker worker = new Worker("Магнус", "Примарх", 1000, 2000);
        Worker worker2 = new Worker("Хорус", "Примарх", 2000, 2000);
        assert worker.getSalary() == 1000;
        worker.increaseSalaryAllWorker();
        assert worker.getSalary() == 1000 * 1.15;
        assert worker2.getSalary() == 2000 * 1.15;
        assert worker.workerAge() == 20;
        System.out.println(worker);
    }
}
