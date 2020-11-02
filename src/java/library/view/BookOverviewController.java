package library.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import library.MainApp;
import library.model.Book;

import java.io.*;


public class BookOverviewController   {
    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, String> bookNameColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> genreColumn;
    @FXML
    private TableColumn<Book, String> yearColumn;
    @FXML
    private TableColumn<Book, String> pubHouseColumn;

    @FXML
    private Label bookNameLabel;
    @FXML
    private Label authorLabel;
    @FXML
    private Label genreLabel;
    @FXML
    private Label yearLabel;
    @FXML
    private Label pubHouseLabel;
    @FXML
    private Label pathLabel;

    @FXML
    private TextArea descrLabel;

    @FXML
    private ImageView imgLabel;

    // Reference to the main application.
    private MainApp mainApp;


    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public BookOverviewController () {
      //  InputStream inStream = getClass().getResourceAsStream("analog.png");
      //  Image imageObject = new Image(inStream);
      //  ImageView imgLabel = new ImageView(imageObject);

    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        bookNameColumn.setCellValueFactory(cellData -> cellData.getValue().bookNameProperty());
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        genreColumn.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
        yearColumn.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asString());
        pubHouseColumn.setCellValueFactory(cellData -> cellData.getValue().pubHouseProperty());


        // Clear person details.
        showBookDetails(null);

        // Listen for selection changes and show the person details when changed.
        bookTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showBookDetails(newValue));


    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        bookTable.setItems(mainApp.getBookData());
    }

    /**
     * Fills all text fields to show details about the book.
     * If the specified book is null, all text fields are cleared.
     *
     * @param book the book or null
     */
    private void showBookDetails(Book book) {
        if (book != null) {
            // Fill the labels with info from the book object.
            bookNameLabel.setText(book.getBookName());
            authorLabel.setText(book.getAuthor());
            genreLabel.setText(book.getGenre());
            yearLabel.setText(Integer.toString(book.getYear()));
            pubHouseLabel.setText(book.getPubHouse());
            descrLabel.setText(book.getDescr());
            pathLabel.setText(book.getBookFile());



            imgLabel.setImage(book.getImage()); //було тут
            //imgLabel.setImage(book.getFile());



            descrLabel.setWrapText(true);
        } else {
            // Book is null, remove all the text.
            bookNameLabel.setText("");
            authorLabel.setText("");
            genreLabel.setText("");
            yearLabel.setText("");
            pubHouseLabel.setText("");
            descrLabel.setText("");
            pathLabel.setText("");

            //File file = new File("/images/logo.jpg");    todo
            //Image image = new Image(file.toURI().toString());
            //imgLabel.setImage(image);



        }
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteBook() {
        int selectedIndex = bookTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            bookTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Book Selected");
            alert.setContentText("Please select a book in the table.");

            alert.showAndWait();
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewBook() throws FileNotFoundException {
        Book tempBook = new Book();
        boolean okClicked = mainApp.showBookEditDialog(tempBook);
        if (okClicked) {
            mainApp.getBookData().add(tempBook);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditBook() {
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            boolean okClicked = mainApp.showBookEditDialog(selectedBook);
            if (okClicked) {
                showBookDetails(selectedBook);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Book Selected");
            alert.setContentText("Please select a book in the table.");

            alert.showAndWait();
        }
    }

  @FXML
  private void handleOpenFileBook() throws IOException {
      //Runtime.getRuntime().exec("explorer.exe /select," + tempFileBook.getBookFile().toString());
      Process p = new ProcessBuilder("explorer.exe", "/select,"+pathLabel.getText()).start();
  }

}