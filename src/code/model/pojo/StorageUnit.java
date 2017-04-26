package code.model.pojo;

/**
 * Created by admin on 22.04.2017.
 */
public class StorageUnit {
    private String author;
    private String title;
    private String publishingHouse;
    private String city;
    private int year;
    private int pagesCount;

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public String getCity() {
        return city;
    }

    public int getYear() {
        return year;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public String getIsn() {
        return isn;
    }

    public String getText() {
        return text;
    }

    private String isn;
    private String text;

    public StorageUnit(String author, String title, String publishingHouse, String city, int year, int pagesCount, String isn, String text) {
        this.author = author;
        this.title = title;
        this.publishingHouse = publishingHouse;
        this.city = city;
        this.year = year;
        this.pagesCount = pagesCount;
        this.isn = isn;
        this.text = text;
    }

    @Override
    public String toString() {
        return "StorageUnit{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", publishingHouse='" + publishingHouse + '\'' +
                ", city='" + city + '\'' +
                ", year=" + year +
                ", pagesCount=" + pagesCount +
                ", isn='" + isn + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
