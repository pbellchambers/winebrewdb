package uk.co.pbellchambers.winebrewdb.util;

import javafx.scene.control.Alert;
import uk.co.pbellchambers.winebrewdb.MainApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UpdateChecker {

    private static final String UPDATE_URL = "https://raw.githubusercontent.com/pbellchambers/winebrewdb/master/VERSION";
    private String latestVersion;

    /**
     * Checks whether there is a newer version of WineBrewDB available
     */
    public void checkForUpdates() {
        latestVersion = getLatestVersion();

        if (latestVersion.equals("")) {
            showProblemCheckingUpdateDialog();
        } else if (latestVersion.equals(MainApp.getInstance().getWineBrewDBVersion())) {
            showNoUpdateRequiredDialog();
        } else {
            showUpdateRequiredDialog();
        }
    }

    /**
     * Gets the latest version available from the update url
     *
     * @return String latest version
     */
    private String getLatestVersion() {
        String version = "";
        try {
            URL updateUrl = new URL(UPDATE_URL);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(updateUrl.openStream()));
            version = bufferedReader.readLine();
            bufferedReader.close();
        } catch (IOException exception) {
            new ErrorHandler("Error accessing update URL", exception);
        }
        return version;
    }

    /**
     * Shows the update required modal dialog
     */
    private void showUpdateRequiredDialog() {
        new ViewLoader().showModalDialog(Alert.AlertType.WARNING, "Update Check",
                                         "Version " + latestVersion + " available!",
                                         "modal/updateRequiredView.fxml");
    }

    /**
     * Shows the no update required modal dialog
     */
    private void showNoUpdateRequiredDialog() {
        new ViewLoader().showModalDialog(Alert.AlertType.INFORMATION, "Update Check",
                                         "No newer version available",
                                         "modal/noUpdateRequiredView.fxml");

    }

    /**
     * Shows the problem checking update modal dialog
     */
    private void showProblemCheckingUpdateDialog() {
        new ViewLoader().showModalDialog(Alert.AlertType.ERROR, "Error",
                                         "Error checking update",
                                         "modal/errorCheckingUpdateView.fxml");

    }
}
