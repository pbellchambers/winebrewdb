package uk.co.pbellchambers.winebrewdb.controller.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

public class CalculatorRootController {

    @FXML
    private TabPane calculatorTabPane;

    public void setSelectedTab(int index) {
        calculatorTabPane.getSelectionModel().clearSelection();
        calculatorTabPane.getSelectionModel().select(index);
    }
}
