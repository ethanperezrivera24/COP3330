// Ethan Perez-Rivera
// 10/17/26
// Represents an individual song

public class Song implements Comparable<Song> {
    private String title;
    private String artist;
    private int year;
    private long sales;
    private long streams;
    
    // Constructor, sets attributes
    public Song(String myTitle, String myArtist, int myYear, long mySales, long myStreams) {
        title = myTitle;
        artist = myArtist;
        year = myYear;
        sales = mySales;
        streams = myStreams;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getYear() {
        return year;
    }

    public long getSales() {
        return sales;
    }

    public long getStreams() {
        return streams;
    }

    // Compare song with other song based on:
    // 1. Sales
    // 2. Streams
    // 3. Title
    public int compareTo(Song other) {
        // 1. Compare by sales
        if (sales != other.sales) {
            return Long.compare(other.sales, sales);
        }
        
        // 2. Compare by streams
        if (streams != other.streams) {
            return Long.compare(other.streams, streams);
        }
        
        // 3. Compare by title alphabetically
        return title.compareTo(other.title);
    }

    // Returns string representation
    public String toString() {
        return title + ", " + artist + " " + year + " " + sales + " " + streams;
    }
}
