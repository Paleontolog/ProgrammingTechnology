package tasks.file3;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import java.io.IOException;
import java.time.LocalDate;

public class Product2 {
    private String name;
    private double price;
    private String manufacturer;

    public Product2() {
        name = "Ересь";
        price = 666.;
        manufacturer = "Хорус";
    }

    public Product2(String name, double price, String manufacturer) {
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }


    public double priceInDollars() {
        try {
            Document document = Jsoup.connect("https://www.cbr-xml-daily.ru/daily.xml").get();
            String xml = document.toString();
            Element el = Jsoup.parse(xml, "", Parser.xmlParser()).getElementById("R01235");
            String price = el.getElementsByTag("Value").text();
            price = price.replace(",", ".");
            return Double.parseDouble(price) * this.price;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(("Cannot parse price, give standard course"));
        }
        return this.price * 66.;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", manufacturer=" + manufacturer +
                '}';
    }

    public static void main(String[] args) throws IOException {
        Product2 product = new Product2("Ересь хоруса", 1000, "Хорус");
        System.out.println(product.priceInDollars());
        System.out.println(product);
    }
}
