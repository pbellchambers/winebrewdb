package uk.co.pbellchambers.winebrewdb.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import uk.co.pbellchambers.winebrewdb.MainApp;

import java.io.IOException;

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
     * Loads and returns a loaded BorderPane
     *
     * @param view the fxml file to be loaded
     * @return the loaded BorderPane
     * @throws IOException if fxml file is unable to be loaded
     */
    public BorderPane loadRootPane(String view) throws IOException {
        fXMLLoader.setLocation(getClass().getResource(VIEW_LOCATION + view));
        return fXMLLoader.load();
    }

    /**
     * Loads a pane and returns the controller
     *
     * @param view the fxml file to be loaded
     * @return the controller of the loaded view
     * @throws IOException if fxml file is unable to be loaded
     */
    public Object loadPane(String view) throws IOException {
        fXMLLoader.setLocation(getClass().getResource(VIEW_LOCATION + view));
        Pane pane = fXMLLoader.load();
        mainApp.setDisplayView(pane);
        return fXMLLoader.getController();
    }

}
