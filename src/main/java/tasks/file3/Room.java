package tasks.file3;

public class Room {
    private int length;
    private int width;
    private int height;

    public Room() {
        length = 0;
        height = 0;
        width = 0;
    }

    public Room(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Room{" +
                "length=" + length +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    public int wallsSquare() {
        return 2 * length * height + 2 * width * height;
    }

    public int wallsSquareWithout() {
        return wallsSquare() - 2 * 15 - 2 * 8;
    }

    public static void main(String[] args) {
        Room room = new Room(5, 6, 8);
        assert room.wallsSquare() == 176;
        assert room.wallsSquareWithout() == 130;
        System.out.println(room);
    }
}
