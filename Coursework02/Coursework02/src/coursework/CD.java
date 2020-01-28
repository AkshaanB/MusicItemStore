package coursework;

import java.util.Objects;

public class CD extends MusicItem { //subclass of MusicItem class
    private int duration;

    public CD(int itemID, String title, String genre, String releaseDate, String artist, double price, int duration) {
        super(itemID, title, genre, releaseDate, artist, price);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CD cd = (CD) o;
        return duration == cd.duration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), duration);
    }

    @Override
    public String toString() {
        return "CD{" +
                "itemID=" + itemID +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", artist='" + artist + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                '}';
    }
}

