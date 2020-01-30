package tasks.file2;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class Worker {
    private String surname;
    private double salary;
    private int year;

    public Worker() {
        surname = "эль Джонсон";
        salary = 0;
        year = 0;
    }

    public Worker(String surname, double salary, int year) {
        this.surname = surname;
        this.salary = salary;
        this.year = year;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getSalary() {
        return salary;
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

    public int getExperience() {
        return new GregorianCalendar().get(Calendar.YEAR) - year;
    }

    public long getTimeAfter() {
        Calendar dateStart = new GregorianCalendar(year + 1, Calendar.JANUARY, 1);
        long diff = new Date().getTime() - dateStart.getTime().getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public String toString() {
        return "Worker{" +
                "surname='" + surname + '\'' +
                ", salary=" + salary +
                ", year=" + year +
                '}';
    }

    public static void main(String[] args) {
        Worker worker = new Worker("Пертурабо", 1000, 2014);
        assert worker.getExperience() == 2020 - 2014;
        assert worker.getTimeAfter() == 1855;
        System.out.println(worker);
    }
}
