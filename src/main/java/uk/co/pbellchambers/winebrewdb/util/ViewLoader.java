package uk.co.pbellchambers.winebrewdb.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ViewLoader extends FXMLLoader {

    private static final String VIEW_LOCATION = "/view/";
    private FXMLLoader fXMLLoader = new FXMLLoader();

    public BorderPane loadBorderPane(String view) throws IOException {
        fXMLLoader.setLocation(getClass().getResource(VIEW_LOCATION + view));
        return fXMLLoader.load();
    }

    public AnchorPane loadAnchorPane(String view) throws IOException {
        fXMLLoader.setLocation(getClass().getResource(VIEW_LOCATION + view));
        return fXMLLoader.load();
    }

}
