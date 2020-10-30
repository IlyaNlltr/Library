package demo.library.model;

import javafx.beans.property.*;
import javafx.scene.image.Image;

import java.io.FileInputStream;
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
    private Image image;//было
    //private Image file;



    //Image image = new Image(getClass().getResourceAsStream("/images/cards/Ad.png"));

    //File file = new File("resouses/images/bookLogo.png");            //это тут было





  // Image image = new Image("file:resouses/images/bookLogo.png");
  // int w = (int)image.getWidth();
  // int h = (int)image.getHeight();
  // Image tile = new WritableImage(image.getPixelReader(), w, h);



    //byte[] buf = new byte[w * h * 4];
    //image.getPixelReader().getPixels(0, 0,w, h, PixelFormat.getByteBgraInstance(), buf, 0, w * 4);

    //Image image = new Image(file.toURI().toString());

    //FileInputStream inputStream = new FileInputStream("/images/logo.jpg");
    //Image image = new Image("/images/logo.jpg");

    /**
     * Default constructor.
     */
    public Book() throws FileNotFoundException {
        this(null, null, null, 0, null, null);
    }

    /**
     * Constructor with some initial data.
     */
    public Book(String bookName, String author, String genre, Integer year, String pupHouse, String bookFile) throws FileNotFoundException {
        this.bookName = new SimpleStringProperty(bookName);
        this.author = new SimpleStringProperty(author);
        this.genre = new SimpleStringProperty(genre);
        this.year = new SimpleIntegerProperty(year);
        this.pubHouse = new SimpleStringProperty(pupHouse);
        this.descr = new SimpleStringProperty("");
        this.bookFile = new SimpleStringProperty(bookFile);
        //this.image = new Image(file.toURI().toString());   //было тут
        this.image = new Image(new FileInputStream("resouses/images/bookLogo.png"));
        //System.out.println(image);



        //this.img = new Image("file:resources/images/logo.jpg");/////////////////////////////////////

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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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

    // public File getFile() {
 //     return file;
 // }

 // public void setFile(File file) {
 //     this.file = file;
 // }
}