package coursework;

public class Date extends MusicItem {  //subclass of MusicItem class
    private int year;
    private int month;
    private int day;

    public Date(int itemID, String title, String genre, String releaseDate, String artist, double price, int year, int month, int day) {
        super(itemID, title, genre, releaseDate, artist, price);
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
}
