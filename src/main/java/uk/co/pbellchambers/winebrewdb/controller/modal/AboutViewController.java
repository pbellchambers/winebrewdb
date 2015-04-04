package uk.co.pbellchambers.winebrewdb.controller.modal;

import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import uk.co.pbellchambers.winebrewdb.MainApp;

public class AboutViewController {

    @FXML
    private Label aboutVersion;

    @FXML
    private Hyperlink aboutHyperlink;

    @FXML
    private void initialize() {
        aboutVersion.setText("WineBrewDB - " + MainApp.getInstance().getWineBrewDBVersion());
    }

    @FXML
    private void clickHyperlink() {
        HostServicesDelegate hostServices = HostServicesFactory.getInstance(MainApp.getInstance());
        hostServices.showDocument(aboutHyperlink.getText());
    }

}
