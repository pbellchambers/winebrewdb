package uk.co.pbellchambers.winebrewdb.util;

import uk.co.pbellchambers.winebrewdb.MainApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DBEngine {

    /**
     * Create a new database using the template blank sqlite database in the location specified by the user
     */
    public void newDatabase() {
        File selectedFile = new ViewLoader().showSaveDialog("Create New Database as...");

        if (!(selectedFile == null)) {
            new FileUtils().saveDataFromInputStream(selectedFile, getClass()
                .getResourceAsStream("/sqlite/BlankWineBrewDBData.sqlite"));
            updateDatabaseLocation(selectedFile.toString());
            new ViewLoader().displayPane("welcomeView.fxml");
        }
    }

    /**
     * Load the database specified by the user
     */
    public void loadDatabase() {
        File selectedFile = new ViewLoader().showOpenDialog();
        if (!(selectedFile == null)) {
            updateDatabaseLocation(selectedFile.toString());
            new ViewLoader().displayPane("welcomeView.fxml");
        }
    }

    /**
     * Save the current database in the location specified by the user
     */
    public void saveDatabase() {
        File selectedFile = new ViewLoader().showSaveDialog("Save Database as...");
        if (!(selectedFile == null)) {
            try {
                new FileUtils().saveDataFromFileInputStream(selectedFile,
                new FileInputStream(new File(MainApp.getInstance().getConfig().getDatabaseLocation())));
                updateDatabaseLocation(selectedFile.toString());
                new ViewLoader().displayPane("welcomeView.fxml");
            } catch (FileNotFoundException exception) {
                new ErrorHandler("Error accessing current database", exception);
            }
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
