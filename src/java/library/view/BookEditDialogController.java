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
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.rendering.ImageType;
//import org.apache.pdfbox.rendering.PDFRenderer;
//import org.apache.pdfbox.tools.imageio.ImageIOUtil;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.rendering.ImageType;
//import org.apache.pdfbox.rendering.PDFRenderer;
//import org.apache.pdfbox.tools.imageio.ImageIOUtil;

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
       // File file = new File("resouses/images/logo.jpg");
       // Image image = new Image(file.toURI().toString());
       // imgField.setImage(image);
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
        yearField.setText(Integer.toString(book.getYear()));
        genreField.setText(book.getGenre());
        pubHouseField.setText(book.getPubHouse());
        descrField.setText(book.getDescr());
        imgField.setImage(book.getImage()); //было
        bookFileField.setText(book.getBookFile());
        //imgField.setImage(new Image("resouses/images/bookLogo.png"));




    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return     
     */
    public boolean isOkClicked() {
        return okClicked;
    }








    @FXML
    private void handleAddImg() throws IOException {

        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.png", "*.bmp");
        chooser.getExtensionFilters().add(extFilter);
        chooser.setTitle("Open File");
        File firstP = chooser.showOpenDialog(new Stage());
        Image img = new Image(firstP.toURI().toString());
        imgField.setImage(img);
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
            book.setBookFile(bookFileField.getText());
            //book.setImage(imgField.getImage()); ////////////////////////////// todo


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
    private void handleAddFile() throws IOException {

        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Book files", "*.pdf", "*.djvu", "*.docx");
        chooser.getExtensionFilters().add(extFilter);
        chooser.setTitle("Open File");
        File bookFile1 = chooser.showOpenDialog(new Stage());
        String bookfile = bookFile1.toURI().toString();
        /////////////todo
        bookFileField.setText(bookfile);
    }

    @FXML
    private void handleOpenBook() throws IOException {
         //если вы хотите запустить это на другой ОС, вам придется использовать Desktop API
        //Runtime.getRuntime().exec("explorer.exe /select," + bookFileField.getText());
        Process p = new ProcessBuilder("explorer.exe", "/select,"+bookFileField.getText()).start();
    }


    //  @FXML
  //  private void handleAddImg() throws IOException {
  //      PDDocument document = PDDocument.load(new File("F:/Zaochka/DIPLOMstech.pdf"));
  //      PDFRenderer pdfRenderer = new PDFRenderer(document);
  //      for (int page = 0; page < document.getNumberOfPages(); ++page) {
  //          BufferedImage bim = pdfRenderer.renderImageWithDPI(
  //                  page, 300, ImageType.RGB);
  //          ImageIOUtil.writeImage(
  //                  bim, String.format("src/output/pdf-%d.%s", page + 1, extension), 300);
  //      }
  //      document.close();
  //
  //  }



}