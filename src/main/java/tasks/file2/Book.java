package tasks.file2;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class Book {
    private String name;
    private int numberOfPages;
    private double price;
    private int year;

    public Book(String name, int numberOfPages, int price, int year) {
        this.name = name;
        this.numberOfPages = numberOfPages;
        this.price = price;
        this.year = year;
    }

    public Book() {
        name = "Noname";
        numberOfPages = 0;
        price = 0;
        year = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPagePrice() {
        return price / numberOfPages;
    }

    public long daysAfterPublishing() {
        Calendar dateStart = new GregorianCalendar(year + 1, Calendar.JANUARY, 1);
        long diff = new Date().getTime() - dateStart.getTime().getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", price=" + price +
                ", year=" + year +
                '}';
    }

    public static void main(String[] args) {
        Book book = new Book("Возвышение Хоруса", 250, 600, 2014);
        assert book.getPagePrice() == 600. / 250.;
        assert book.daysAfterPublishing() == 1855;
        System.out.println(book);
    }
}
