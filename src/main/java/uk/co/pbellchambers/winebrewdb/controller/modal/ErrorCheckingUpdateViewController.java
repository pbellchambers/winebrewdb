package uk.co.pbellchambers.winebrewdb.controller.modal;

import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import uk.co.pbellchambers.winebrewdb.MainApp;

public class ErrorCheckingUpdateViewController {

    @FXML
    private Hyperlink releasesLink;

    @FXML
    private void clickHyperlink() {
        HostServicesDelegate hostServices = HostServicesFactory.getInstance(MainApp.getInstance());
        hostServices.showDocument(releasesLink.getText());
    }

}
