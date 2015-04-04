package uk.co.pbellchambers.winebrewdb.controller;

import javafx.fxml.FXML;
import uk.co.pbellchambers.winebrewdb.controller.calculator.CalculatorRootController;
import uk.co.pbellchambers.winebrewdb.util.ViewLoader;

import java.io.IOException;

public class RootController {

    @FXML
     private void showBrewRootView() {
        try {
            new ViewLoader().loadPane("brew/brewRootView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showLedgerRootView() {
        try {
            new ViewLoader().loadPane("ledger/ledgerRootView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showRecipeRootView() {
        try {
            new ViewLoader().loadPane("recipe/recipeRootView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showCalculatorAlcoholTab() {
        try {
            CalculatorRootController calculatorRootController = (CalculatorRootController) new ViewLoader().loadPane(
                "calculator/calculatorRootView.fxml");
            calculatorRootController.setSelectedTab(0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showCalculatorDilutionTab() {
        try {
            CalculatorRootController calculatorRootController = (CalculatorRootController) new ViewLoader().loadPane(
                "calculator/calculatorRootView.fxml");
            calculatorRootController.setSelectedTab(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showCalculatorMeasuresTab() {
        try {
            CalculatorRootController calculatorRootController = (CalculatorRootController) new ViewLoader().loadPane(
                "calculator/calculatorRootView.fxml");
            calculatorRootController.setSelectedTab(2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showCalculatorSugarTab() {
        try {
            CalculatorRootController calculatorRootController = (CalculatorRootController) new ViewLoader().loadPane(
                "calculator/calculatorRootView.fxml");
            calculatorRootController.setSelectedTab(3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showCalculatorSgTab() {
        try {
            CalculatorRootController calculatorRootController = (CalculatorRootController) new ViewLoader().loadPane(
                "calculator/calculatorRootView.fxml");
            calculatorRootController.setSelectedTab(4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showInformationRootView() {
        try {
            new ViewLoader().loadPane("information/informationRootView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
