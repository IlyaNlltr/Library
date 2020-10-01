package library.view;

import javafx.scene.image.ImageView;
import library.model.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import Pdf2Dom.*;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
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
    private ImageView imgField;    //////////////////////


    private Stage dialogStage;
    private Book book;
    private boolean okClicked = false;


    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

    }

    /**
     * Sets the stage of this dialog.
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        descrField.setWrapText(true);



        // Set the dialog icon.
        this.dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));
    }

    /**
     * Sets the book to be edited in the dialog.
     *
     * @param book
     */
    public void setBook(Book book) {
        this.book = book;

        bookNameField.setText(book.getBookName());
        authorField.setText(book.getAuthor());
        yearField.setText(String.valueOf(book.getYear()));
        genreField.setText(book.getGenre());
        pubHouseField.setText(book.getPubHouse());
        descrField.setText(book.getDescr());
        imgField.setImage(book.getImg());   ///////////////////

    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */


    @FXML
    private void handleOk() {
        if (isInputValid()) {
            book.setBookName(bookNameField.getText());
            book.setAuthor(authorField.getText());
            book.setYear(Integer.parseInt(yearField.getText()));
            book.setGenre(genreField.getText());
            book.setPubHouse(pubHouseField.getText());
            book.setDescr(descrField.getText());
            book.setImg(imgField.getImage()); //////////////////////////////


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
            errorMessage += "No valid first name!\n";
        }
        if (authorField.getText() == null || authorField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (genreField.getText() == null || genreField.getText().length() == 0) {
            errorMessage += "No valid street!\n";
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
            errorMessage += "No valid city!\n";
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
    private void handleAddImg(){

    }



}