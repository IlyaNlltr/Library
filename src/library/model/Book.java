package library.model;

import javafx.beans.property.*;
import javafx.scene.image.Image;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import java.io.InputStream;

/**
 * Model class for a Book.
 */
public class Book {

    private final StringProperty bookName;
    private final StringProperty author;
    private final IntegerProperty year;
    private final StringProperty genre;
    private final StringProperty pubHouse;
    private final StringProperty descr;
    private Image img; ////////////////////////////////////


    /**
     * Default constructor.
     */
    public Book() {
        this(null, null, null, 0, null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param bookName
     * @param author
     */
    public Book(String bookName, String author, String genre, Integer year, String pupHouse) {
        this.bookName = new SimpleStringProperty(bookName);
        this.author = new SimpleStringProperty(author);
        this.genre = new SimpleStringProperty(genre);
        this.year = new SimpleIntegerProperty(year);
        this.pubHouse = new SimpleStringProperty(pupHouse);
        this.descr = new SimpleStringProperty("");
        this.img = new Image("");

        // Some initial dummy data, just for convenient testing.
        // this.year = new SimpleIntegerProperty(1234);
        // this.genre = new SimpleStringProperty("some street");
        // this.pubHouse = new SimpleStringProperty("some city");

    }

    public String getBookName() {
        return bookName.get();
    }

    public StringProperty bookNameProperty() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName.set(bookName);
    }

    public String getAuthor() {
        return author.get();
    }

    public StringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public int getYear() {
        return year.get();
    }

    public IntegerProperty yearProperty() {
        return year;
    }

    public void setYear(int year) {
        this.year.set(year);
    }

    public String getGenre() {
        return genre.get();
    }

    public StringProperty genreProperty() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }

    public String getPubHouse() {
        return pubHouse.get();
    }

    public StringProperty pubHouseProperty() {
        return pubHouse;
    }

    public void setPubHouse(String pubHouse) {
        this.pubHouse.set(pubHouse);
    }

    public String getDescr() {
        return descr.get();
    }

    public StringProperty descrProperty() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr.set(descr);
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
}