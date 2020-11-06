package library.view;

import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import library.model.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

/**
 * Dialog to edit details of a book.
 */
public class BookEditDialogController {

    @FXML
    private TextField bookNameField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField yearField;
    @FXML
    private TextField genreField;
    @FXML
    private TextField pubHouseField;
    @FXML
    private TextArea descrField;

    @FXML
    private TextField bookFileField;


    private Stage dialogStage;
    private Book book;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        descrField.setWrapText(true);

        // Set the dialog icon.
        this.dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));
    }

    public void setBook(Book book) {
        this.book = book;

        bookNameField.setText(book.getBookName());
        authorField.setText(book.getAuthor());
        yearField.setText(Integer.toString(book.getYear()));
        genreField.setText(book.getGenre());
        pubHouseField.setText(book.getPubHouse());
        descrField.setText(book.getDescr());
        bookFileField.setText(book.getBookFile());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            book.setBookName(bookNameField.getText());
            book.setAuthor(authorField.getText());
            book.setYear(Integer.parseInt(yearField.getText()));
            book.setGenre(genreField.getText());
            book.setPubHouse(pubHouseField.getText());
            book.setDescr(descrField.getText());
            book.setBookFile(bookFileField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (bookNameField.getText() == null || bookNameField.getText().length() == 0) {
            errorMessage += "No valid book name!\n";
        }
        if (authorField.getText() == null || authorField.getText().length() == 0) {
            errorMessage += "No valid author!\n";
        }
        if (genreField.getText() == null || genreField.getText().length() == 0) {
            errorMessage += "No valid genre!\n";
        }

        Calendar cal = Calendar.getInstance();

        if (Integer.parseInt(yearField.getText()) > cal.getWeekYear() || Integer.parseInt(yearField.getText()) < 1500 || yearField.getText() == null || yearField.getText().length() == 0) {
            errorMessage += "No valid year!\n";
        } else {
            // try to parse the year into an int.
            try {
                Integer.parseInt(yearField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid year (must be an integer)!\n";
            }
        }

        if (pubHouseField.getText() == null || pubHouseField.getText().length() == 0) {
            errorMessage += "No valid pub house!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    @FXML
    private void handleAddFile() throws IOException {

        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Book files", "*.pdf", "*.djvu", "*.docx");
        chooser.getExtensionFilters().add(extFilter);
        chooser.setTitle("Open File");
        File bookFile1 = chooser.showOpenDialog(new Stage());
        String bookfile = bookFile1.toURI().toString();
        bookFileField.setText(bookfile);
    }

    @FXML
    private void handleOpenBook() throws IOException {
        Process p = new ProcessBuilder("explorer.exe", "/select," + bookFileField.getText()).start();
    }
}