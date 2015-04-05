package uk.co.pbellchambers.winebrewdb.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import uk.co.pbellchambers.winebrewdb.MainApp;

public class ViewLoader extends FXMLLoader {

    private static final String VIEW_LOCATION = "/view/";
    private FXMLLoader fXMLLoader = new FXMLLoader();
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
     * @return the loaded pane
     */
    public Pane loadPane(String view) {
        fXMLLoader.setLocation(getClass().getResource(VIEW_LOCATION + view));
        Pane pane = null;
        try {
            pane = fXMLLoader.load();
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
        return fXMLLoader.getController();
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
}
