package uk.co.pbellchambers.winebrewdb.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import uk.co.pbellchambers.winebrewdb.MainApp;

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
        GridPane exceptionContent = createExceptionContent(exceptionText);
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
    private GridPane createExceptionContent(String exceptionText) {
        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        return expContent;
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
