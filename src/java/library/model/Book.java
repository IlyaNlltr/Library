package library.model;

import javafx.beans.property.*;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;

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
    private final StringProperty bookFile;

    /**
     * Default constructor.
     */
    public Book() throws FileNotFoundException {
        this(null, null, null, 0, null, null);
    }

    /**
     * Constructor with some initial data.
     */
    public Book(String bookName, String author, String genre, Integer year, String pupHouse, String bookFile) {
        this.bookName = new SimpleStringProperty(bookName);
        this.author = new SimpleStringProperty(author);
        this.genre = new SimpleStringProperty(genre);
        this.year = new SimpleIntegerProperty(year);
        this.pubHouse = new SimpleStringProperty(pupHouse);
        this.descr = new SimpleStringProperty("");
        this.bookFile = new SimpleStringProperty(bookFile);
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

    public String getBookFile() {
        return bookFile.get();
    }

    public StringProperty bookFileProperty() {
        return bookFile;
    }

    public void setBookFile(String bookFile) {
        this.bookFile.set(bookFile);
    }

}