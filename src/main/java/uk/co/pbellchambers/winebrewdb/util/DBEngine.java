package uk.co.pbellchambers.winebrewdb.util;

import uk.co.pbellchambers.winebrewdb.MainApp;

import java.io.File;

public class DBEngine {

    /**
     * Create a new database using the template blank sqlite database in the location specified by the user
     */
    public void newDatabase() {
        File selectedFile = new ViewLoader().showSaveDialog("Create New Database as...");

        if (!(selectedFile == null)) {
            new FileUtils().saveDataFromStream(selectedFile, getClass().getResourceAsStream("/sqlite/BlankWineBrewDBData.sqlite"));
            updateDatabaseLocation(selectedFile.toString());
        }
    }

    /**
     * Load the database specified by the user
     */
    public void loadDatabase() {
        File selectedFile = new ViewLoader().showOpenDialog();
        if (!(selectedFile == null)) {
            updateDatabaseLocation(selectedFile.toString());
        }
    }

    /**
     * Save the current database in the location specified by the user
     */
    public void saveDatabase() {
        File selectedFile = new ViewLoader().showSaveDialog("Save Database as...");
        if (!(selectedFile == null)) {

        }
    }

    /**
     * Updates the database location config with the new location
     *
     * @param databaseLocation location of the database
     */
    public void updateDatabaseLocation(String databaseLocation) {
        MainApp mainApp = MainApp.getInstance();
        mainApp.getConfig().setDatabaseLocation(databaseLocation);
        mainApp.getRootController().enableAll();
        mainApp.setTitle();
    }
}
