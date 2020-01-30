package tasks.file3;

import java.time.LocalDate;

public class Product {
    private String name;
    private double price;
    private int year;

    public Product() {
        name = "Ересь";
        price = 666.;
        year = 30000;
    }

    public Product(String name, double price, int year) {
        this.name = name;
        this.price = price;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int countYears() {
        return LocalDate.now().getYear() - year;
    }

    public void increasePrice() {
        if (LocalDate.now().getYear() == year) {
            price *= 1.2;
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", year=" + year +
                '}';
    }

    public static void main(String[] args) {
        Product product = new Product("Ересь хоруса", 1000, 2020);
        product.increasePrice();
        assert product.getPrice() == 1200;
        Product product1 = new Product("Сожжение Просперо", 1000, 2010);
        assert  product1.countYears() == 10;
        product1.increasePrice();
        assert product1.getPrice() == 1000;
        System.out.println(product);
    }
}
