package uk.co.pbellchambers.winebrewdb.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import uk.co.pbellchambers.winebrewdb.MainApp;

public class WelcomeViewController {

    @FXML
    private Label welcomeDatabaseLocation;

    @FXML
    private Label welcomeMessage;

    @FXML
    private HBox welcomeButtonBox;

    @FXML
    private void initialize() {
        String databaseLocation = MainApp.getInstance().getConfig().getDatabaseLocation();
        if(databaseLocation.equals("")) {
            welcomeDatabaseLocation.setText("Your current database is not set!");
            welcomeMessage.setText("Please create a new database below to begin.");
            welcomeButtonBox.setVisible(true);
        } else {
            welcomeDatabaseLocation.setText("Your current database is set to: " + databaseLocation);
            welcomeMessage.setText("Select an option from the left to begin, and remember to ensure your database is backed up.");
            welcomeButtonBox.setVisible(false);
        }
    }


}
