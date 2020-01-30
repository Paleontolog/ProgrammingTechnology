package tasks.file2;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Date1 {
    private int day;
    private int month;
    private int year;

    public Date1() {
        day = 0;
        month = 0;
        year = 0;
    }

    public Date1(int day, int month, int year) throws DateTimeException {
        if (month < 1 || month > 12) throw new DateTimeException("Incorrect month");
        if (day < 1 || day > daysCount(month, year)) throw new DateTimeException("Incorrect day");
        this.day = day;
        this.month = month;
        this.year = year;
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    private int daysCount(int month, int year) {
        if (month == 2) {
            return isLeapYear(year) ? 29 : 28;
        }
        return 30 + (month > 7 ? month + 1 : month) % 2;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Date1{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }

    public void addYear() {
        year += 1;
    }

    public void minusTwoDays() {
        LocalDate localDate = LocalDate.of(year, month, day).minusDays(2);
        year = localDate.getYear();
        month = localDate.getMonthValue();
        day = localDate.getDayOfMonth();
    }

    public static void main(String[] args) {
        Date1 date1 = new Date1(31, 12, 1997);
        assert date1.getYear() == 1997;
        date1.addYear();
        assert date1.getYear() == 1998;
        date1.minusTwoDays();
        assert date1.getDay() == 29;
        Date1 date11 = new Date1(1, 1, 1997);
        date11.minusTwoDays();
        assert date11.getYear() == 1996;
        assert date11.getMonth() == 12;
        assert date11.getDay() == 30;
        System.out.println(date1);
    }
}
