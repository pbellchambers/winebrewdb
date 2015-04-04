package uk.co.pbellchambers.winebrewdb.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import uk.co.pbellchambers.winebrewdb.controller.calculator.CalculatorRootController;
import uk.co.pbellchambers.winebrewdb.util.ViewLoader;

public class RootController {

    @FXML
    private void exitApplication() {
        Platform.exit();
    }

    @FXML
    private void showAboutView() {
        new ViewLoader().showModalDialog("modal/aboutView.fxml");
    }

    @FXML
     private void showBrewRootView() {
        new ViewLoader().loadPane("brew/brewRootView.fxml");
    }

    @FXML
    private void showLedgerRootView() {
        new ViewLoader().loadPane("ledger/ledgerRootView.fxml");
    }

    @FXML
    private void showRecipeRootView() {
        new ViewLoader().loadPane("recipe/recipeR2ootView.fxml");
    }

    @FXML
    private void showCalculatorAlcoholTab() {
        CalculatorRootController calculatorRootController = (CalculatorRootController) new ViewLoader().loadPane(
            "calculator/calculatorRootView.fxml");
        calculatorRootController.setSelectedTab(0);
    }

    @FXML
    private void showCalculatorDilutionTab() {
        CalculatorRootController calculatorRootController = (CalculatorRootController) new ViewLoader().loadPane(
            "calculator/calculatorRootView.fxml");
        calculatorRootController.setSelectedTab(1);
    }

    @FXML
    private void showCalculatorMeasuresTab() {
        CalculatorRootController calculatorRootController = (CalculatorRootController) new ViewLoader().loadPane(
            "calculator/calculatorRootView.fxml");
        calculatorRootController.setSelectedTab(2);
    }

    @FXML
    private void showCalculatorSugarTab() {
        CalculatorRootController calculatorRootController = (CalculatorRootController) new ViewLoader().loadPane(
            "calculator/calculatorRootView.fxml");
        calculatorRootController.setSelectedTab(3);
    }

    @FXML
    private void showCalculatorSgTab() {
        CalculatorRootController calculatorRootController = (CalculatorRootController) new ViewLoader().loadPane(
            "calculator/calculatorRootView.fxml");
        calculatorRootController.setSelectedTab(4);
    }

    @FXML
    private void showInformationRootView() {
        new ViewLoader().loadPane("information/informationRootView.fxml");
    }
}
