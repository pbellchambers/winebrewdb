package uk.co.pbellchambers.winebrewdb.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import uk.co.pbellchambers.winebrewdb.MainApp;

import java.io.IOException;

public class ViewLoader extends FXMLLoader {

    private static final String VIEW_LOCATION = "/view/";
    private FXMLLoader fXMLLoader = new FXMLLoader();

    public BorderPane loadRootPane(String view) throws IOException {
        fXMLLoader.setLocation(getClass().getResource(VIEW_LOCATION + view));
        return fXMLLoader.load();
    }

    public Object loadPane(String view) throws IOException {
        fXMLLoader.setLocation(getClass().getResource(VIEW_LOCATION + view));
        Pane pane = fXMLLoader.load();
        MainApp.setDisplayView(pane);
        return fXMLLoader.getController();
    }

}
