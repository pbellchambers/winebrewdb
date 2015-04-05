package uk.co.pbellchambers.winebrewdb.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import uk.co.pbellchambers.winebrewdb.MainApp;
import uk.co.pbellchambers.winebrewdb.controller.modal.ErrorViewController;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorHandler {

    /**
     * Creates a new error modal dialog
     *
     * @param errorDescription the string description of the error
     * @param exception the exception thrown
     */
    public ErrorHandler(String errorDescription, Exception exception) {
        String exceptionText = exceptionToString(exception);
        GridPane exceptionContent = loadExceptionContent(exceptionText);
        createAlert(errorDescription, exceptionContent);
    }

    /**
     * Converts the exception to a string
     *
     * @param exception the exception thrown
     * @return string of the exception
     */
    private String exceptionToString(Exception exception) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        exception.printStackTrace(printWriter);
        return stringWriter.toString();
    }

    /**
     * Creates the GridPane content to hold the exception
     *
     * @param exceptionText the string content of the exception
     * @return the gridpane containing the exception content
     */
    private GridPane loadExceptionContent(String exceptionText) {
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("/view/modal/errorView.fxml"));
        GridPane gridPane = null;
        try {
            gridPane = fXMLLoader.load();
        } catch (Exception exception) {
            new ErrorHandler("Unable to load view", exception);
        }
        ErrorViewController errorViewController = fXMLLoader.getController();
        errorViewController.setErrorStacktrace(exceptionText);

        return gridPane;
    }

    /**
     * Creates the alert to display the error
     *
     * @param errorDescription a human understandable description of the error
     * @param exceptionContent the GridPane containing the error content
     */
    private void createAlert(String errorDescription, GridPane exceptionContent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An error has occurred");
        alert.setContentText(errorDescription);
        alert.getDialogPane().setExpandableContent(exceptionContent);
        alert.initOwner(MainApp.getInstance().getPrimaryStage());
        alert.showAndWait();
    }
}
