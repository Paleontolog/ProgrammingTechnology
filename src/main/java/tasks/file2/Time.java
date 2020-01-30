package tasks.file2;

public class Time {
    private int seconds;
    private int minutes;
    private int hours;

    public Time(int seconds, int minutes, int hours) {
        this.seconds = seconds;
        this.minutes = minutes;
        this.hours = hours;
    }

    public Time() {
        seconds = 0;
        minutes = 0;
        hours = 0;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "Time{" +
                "seconds=" + seconds +
                ", minutes=" + minutes +
                ", hours=" + hours +
                '}';
    }

    public int getSecondsCount() {
        return seconds + minutes * 60 + hours * 3600;
    }

    public void addFiveSecond() {
        int newSeconds = seconds + 5;
        seconds = newSeconds % 60;
        int newMinutes = minutes + newSeconds / 60;
        minutes = newMinutes % 60;
        hours += newMinutes / 60;
    }

    public static void main(String[] args) {
        Time time = new Time(56, 59, 5);
        assert 56 + 59 * 60 + 5 * 3600 == time.getSecondsCount();

        time.addFiveSecond();
        assert time.getSeconds() == 1;
        assert time.getMinutes() == 0;
        assert time.getHours() == 6;
        assert 1 + 6 * 3600 == time.getSecondsCount();

        Time time1 = new Time();
        assert time1.getSeconds() == 0;
        assert time1.getMinutes() == 0;
        assert time1.getHours() == 0;

        System.out.println(time);
    }
}
