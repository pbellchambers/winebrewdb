package uk.co.pbellchambers.winebrewdb.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import uk.co.pbellchambers.winebrewdb.MainApp;
import uk.co.pbellchambers.winebrewdb.util.DBEngine;

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
            MainApp.getInstance().getRootController().disableAll();
            welcomeDatabaseLocation.setText("Your current database is not set!");
            welcomeDatabaseLocation.setTextFill(Color.RED);
            welcomeDatabaseLocation.setFont(Font.font("", FontWeight.BOLD, 12));
            welcomeMessage.setText("Please create a new database below to begin.");
            welcomeButtonBox.setVisible(true);
        } else {
            MainApp.getInstance().getRootController().enableAll();
            welcomeDatabaseLocation.setText("Your current database is set to: " + databaseLocation);
            welcomeMessage.setText(
                "Select an option from the left to begin, and remember to ensure your database is backed up.");
            welcomeButtonBox.setVisible(false);
        }
    }

    @FXML
    private void newDatabase() {
        new DBEngine().newDatabase();
    }

    @FXML
    private void loadDatabase() {
        new DBEngine().loadDatabase();
    }
}
