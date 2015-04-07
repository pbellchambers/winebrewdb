package uk.co.pbellchambers.winebrewdb.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import uk.co.pbellchambers.winebrewdb.MainApp;

import java.io.File;

public class ViewLoader extends FXMLLoader {

    private static final String VIEW_LOCATION = "/view/";
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private MainApp mainApp;

    /**
     * Constructor
     */
    public ViewLoader() {
        this.mainApp = MainApp.getInstance();
    }

    /**
     * Loads a pane and returns the loaded pane
     *
     * @param view the fxml file to be loaded
     * @return Pane the loaded view
     */
    public Pane loadPane(String view) {
        fxmlLoader.setLocation(getClass().getResource(VIEW_LOCATION + view));
        Pane pane = null;
        try {
            pane = fxmlLoader.load();
        } catch (Exception exception) {
            new ErrorHandler("Unable to load view", exception);
        }
        return pane;
    }

    /**
     * Loads and displays a pane and returns the controller
     *
     * @param view the fxml file to be loaded
     * @return the controller of the loaded view
     */
    public Object displayPane(String view) {
        Pane pane = loadPane(view);
        mainApp.setDisplayView(pane);
        return fxmlLoader.getController();
    }

    /**
     * Loads and displays the root layout and sets the controller
     *
     * @param view the fxml file to be loaded
     * @return BorderPane the loaded view
     */
    public BorderPane loadRootLayout(String view) {
        fxmlLoader.setLocation(getClass().getResource(VIEW_LOCATION + view));
        BorderPane pane = null;
        try {
            pane = fxmlLoader.load();
            MainApp.getInstance().setRootController(fxmlLoader.getController());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return pane;
    }

    /**
     * Shows a modal dialog using the specified view
     *
     * @param alertType the type of the alert to display
     * @param title the title of the dialog
     * @param header the header of the dialog
     * @param view the fxml file to be loaded
     */
    public void showModalDialog(Alert.AlertType alertType, String title, String header, String view) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.getDialogPane().setContent(loadPane(view));
        alert.initOwner(MainApp.getInstance().getPrimaryStage());
        alert.showAndWait();
    }

    /**
     * Shows a file open dialog and returns the selected file
     *
     * @return the selected file
     */
    public File showOpenDialog() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Database");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("SQLite Files", "*.sqlite"),
                                                 new FileChooser.ExtensionFilter("All Files", "*.*"));
        return fileChooser.showOpenDialog(MainApp.getInstance().getPrimaryStage());
    }

    /**
     * Shows the file save dialog and returns the selected file
     *
     * @param title the dialog window title
     * @return the selected file
     */
    public File showSaveDialog(String title) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("SQLite Files", "*.sqlite"));
        return fileChooser.showSaveDialog(MainApp.getInstance().getPrimaryStage());
    }
}
