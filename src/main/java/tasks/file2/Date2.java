package tasks.file2;

import java.time.DateTimeException;

public class Date2 {
    private int day;
    private int month;
    private int year;

    public Date2() {
        day = 0;
        month = 0;
        year = 0;
    }

    public Date2(int day, int month, int year) throws DateTimeException {
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

    public void addMonth() {
        int newMonth = month + 1;
        month = newMonth % 12;
        year += newMonth / 12;
    }

    public boolean isNumOfDaysEqualsNumOfMonth() {
        return month == day;
    }

    public static void main(String[] args) {
        Date2 date1 = new Date2(12, 12, 1997);
        assert date1.isNumOfDaysEqualsNumOfMonth();
        date1.addMonth();
        assert date1.getYear() == 1998;
        System.out.println(date1);
    }
}
