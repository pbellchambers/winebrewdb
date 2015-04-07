package uk.co.pbellchambers.winebrewdb.util;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;

public class Config {

    private File configDirectory;
    private File configFile;
    private Wini config;
    private static final String CONFIG_FILENAME = "WineBrewDBConfig.ini";
    private static final String OS = System.getProperty("os.name").toUpperCase();

    private String databaseLocation;

    /**
     * Constructor, set config location and create the ini if it doesn't exist
     */
    public Config() {
        setConfigLocation();
        createConfigIni();
        loadConfig();
    }

    /**
     * Sets the config location depending on OS
     */
    private void setConfigLocation() {
        if (OS.contains("WIN")) {
            configDirectory = new File(System.getenv("APPDATA") + File.separator + "WineBrewDB");
        } else if (OS.contains("MAC")) {
            configDirectory = new File(System.getProperty("user.home") + "/Library/Application/WineBrewDB");
        } else if (OS.contains("NUX")) {
            configDirectory = new File(System.getProperty("user.home") + "/.WineBrewDB");
        } else {
            configDirectory = new File(System.getProperty("user.home"));
        }

        configFile = new File(configDirectory, CONFIG_FILENAME);
    }

    /**
     * Creates the config ini if it doesn't already exist
     */
    private void createConfigIni() {
        if (!configFile.exists()) {
            new FileUtils().createDirectory(configDirectory);
            new FileUtils().saveDataFromInputStream(configFile,
                                                    getClass().getResourceAsStream("/config/WineBrewDBConfig.ini"));
        }
    }

    /**
     * Loads the config from the specified ini file
    */
    private void loadConfig() {
        try {
            config = new Wini(configFile);
        } catch (InvalidFileFormatException exception) {
            new ErrorHandler(configFile + " file format is invalid", exception);
        } catch (IOException exception) {
            new ErrorHandler("Error accessing: " + configFile + " Please check the file exists and you have permission", exception);
        }

        setDatabaseLocation(config.get("WineBrewDB", "DatabaseLocation"));
    }

    /**
     * Gets the current database location
     *
     * @return String database location
     */
    public String getDatabaseLocation() {
        return databaseLocation;
    }

    /**
     * Sets the current database location
     *
     * @param databaseLocation String database location
     */
    public void setDatabaseLocation(String databaseLocation) {
        try {
            config.put("WineBrewDB", "DatabaseLocation", databaseLocation);
            config.store();
            this.databaseLocation = databaseLocation;
        } catch (IOException exception) {
            new ErrorHandler("Error saving settings, please try again: " + configFile, exception);
        }
    }


}
