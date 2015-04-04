package uk.co.pbellchambers.winebrewdb.controller.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

public class CalculatorRootController {

    @FXML
    private TabPane calculatorTabPane;

    /**
     * Sets the selected tab on the calculatorTabPane
     *
     * @param index the index of the tab to be selected
     */
    public void setSelectedTab(int index) {
        calculatorTabPane.getSelectionModel().select(index);
    }
}
