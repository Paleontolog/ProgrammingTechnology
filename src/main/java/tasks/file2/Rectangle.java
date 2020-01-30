package tasks.file2;

public class Rectangle {
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public Rectangle(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public Rectangle() {
        x1 = 0;
        y1 = 0;
        x2 = 1;
        y2 = 1;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    private int height() {
        return Math.abs(y2 - y1);
    }

    private int width() {
        return Math.abs(x2 - x1);
    }

    public int getArea() {
        return width() * height();
    }

    public boolean isSquare() {
        return height() == width();
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "x1=" + x1 +
                ", y1=" + y1 +
                ", x2=" + x2 +
                ", y2=" + y2 +
                '}';
    }

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(3, 2, 7, 6);
        assert rectangle.isSquare();
        assert rectangle.getArea() == 16;
        rectangle.setX1(5);
        assert !rectangle.isSquare();
        assert rectangle.getArea()  == 2 * 4;

        System.out.println(rectangle);
    }
}

