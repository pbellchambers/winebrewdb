package uk.co.pbellchambers.winebrewdb.controller.modal;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ErrorViewController {

    @FXML
    private TextArea errorStacktrace;

    @FXML
    public void setErrorStacktrace(String stacktrace) {
        errorStacktrace.setText(stacktrace);
    }

}
