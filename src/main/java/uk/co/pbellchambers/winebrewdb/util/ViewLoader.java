package uk.co.pbellchambers.winebrewdb.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
     * Loads and returns a loaded BorderPane
     *
     * @param view the fxml file to be loaded
     * @return the loaded BorderPane
     */
    public BorderPane loadRootPane(String view) {
        fXMLLoader.setLocation(getClass().getResource(VIEW_LOCATION + view));
        try {
            return fXMLLoader.load();
        } catch (Exception exception) {
            new ErrorHandler("Unable to load view", exception);
        }
        return null;
    }

    /**
     * Loads a pane and returns the controller
     *
     * @param view the fxml file to be loaded
     * @return the controller of the loaded view
     */
    public Object loadPane(String view) {
        fXMLLoader.setLocation(getClass().getResource(VIEW_LOCATION + view));
        Pane pane = null;
        try {
            pane = fXMLLoader.load();
        } catch (Exception exception) {
            new ErrorHandler("Unable to load view", exception);
        }
        mainApp.setDisplayView(pane);
        return fXMLLoader.getController();
    }

    /**
     * Shows a modal dialog view
     *
     * @param view the fxml file to be loaded
     */
    public void showModalDialog(String view) {
        BorderPane borderPane = loadRootPane(view);
        Stage primaryStage = MainApp.getInstance().getPrimaryStage();
        Stage newStage = new Stage();
        Scene scene = new Scene(borderPane);

        newStage.setScene(scene);
        newStage.setResizable(false);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.initStyle(StageStyle.UTILITY);
        newStage.initOwner(primaryStage);
        newStage.setX(primaryStage.getX() + (primaryStage.getWidth() / 2) - (borderPane.getPrefWidth() / 2));
        newStage.setY(primaryStage.getY() + (primaryStage.getHeight() / 2) - (borderPane.getPrefHeight() / 2));
        newStage.showAndWait();
    }
}
