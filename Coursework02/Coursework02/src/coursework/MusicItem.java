package coursework;

import java.util.Objects;

public abstract class MusicItem implements Comparable<MusicItem>{  //MusicItem is the super class
    protected int itemID;
    protected String title;
    protected String genre;
    protected String releaseDate;
    protected String artist;
    protected double price;


    public MusicItem(int itemID, String title, String genre, String releaseDate, String artist, double price) {
        super();
        this.itemID = itemID;
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.artist = artist;
        this.price = price;
    }
    public MusicItem(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicItem item = (MusicItem) o;
        return itemID == item.itemID &&
                Double.compare(item.price, price) == 0 &&
                Objects.equals(title, item.title) &&
                Objects.equals(genre, item.genre) &&
                Objects.equals(releaseDate, item.releaseDate) &&
                Objects.equals(artist, item.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemID, title, genre, releaseDate, artist, price);
    }

    @Override
    public String toString() {
        return "MusicItem{" +
                "itemID=" + itemID +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", artist='" + artist + '\'' +
                ", price=" + price +
                '}';
    }

    public int getItemID() {
        return itemID;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getArtist() {
        return artist;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public int compareTo(MusicItem o) {
        return title.compareTo(o.getTitle());
    }
}
