package tasks.file3;

public class ProperFraction {
    private double numerator;
    private double denominator;

    public ProperFraction() {
        numerator = 0;
        denominator = 1;
    }

    public ProperFraction(int numerator, int denominator) {
        assert denominator != 0 : "Denominator must not be zero!";
        assert numerator < denominator : "Numerator must be smaller than denominator!";
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public double getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public double getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    @Override
    public String toString() {
        return "ProperFraction{" +
                "numerator=" + numerator +
                ", denominator=" + denominator +
                '}';
    }

    public double toPercent() {
        return numerator / denominator * 100;
    }

    public double sumNumeratorAndDenominator() {
        return numerator + denominator;
    }

    public static void main(String[] args) {
        ProperFraction properFraction = new ProperFraction(1, 5);
        assert properFraction.toPercent() == 20.;
        assert properFraction.sumNumeratorAndDenominator() == 6;
        System.out.println(properFraction);
    }
}
