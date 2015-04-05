package uk.co.pbellchambers.winebrewdb.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import uk.co.pbellchambers.winebrewdb.MainApp;
import uk.co.pbellchambers.winebrewdb.controller.calculator.CalculatorRootController;
import uk.co.pbellchambers.winebrewdb.util.ViewLoader;

public class RootController {

    @FXML
    private void exitApplication() {
        Platform.exit();
    }

    @FXML
    private void showAboutView() {
        new ViewLoader().showModalDialog(Alert.AlertType.INFORMATION, "About",
                                         "WineBrewDB - " + MainApp.getInstance().getWineBrewDBVersion(),
                                         "modal/aboutView.fxml");
    }

    @FXML
    private void showCreditsView() {
        new ViewLoader().showModalDialog(Alert.AlertType.INFORMATION,
                                         "Credits",
                                         "Credits",
                                         "modal/creditsView.fxml");
    }

    @FXML
     private void showBrewRootView() {
        new ViewLoader().displayPane("brew/brewRootView.fxml");
    }

    @FXML
    private void showLedgerRootView() {
        new ViewLoader().displayPane("ledger/ledgerRootView.fxml");
    }

    @FXML
    private void showRecipeRootView() {
        new ViewLoader().displayPane("recipe/recipeRootView.fxml");
    }

    @FXML
    private void showCalculatorAlcoholTab() {
        CalculatorRootController calculatorRootController = (CalculatorRootController) new ViewLoader().displayPane(
            "calculator/calculatorRootView.fxml");
        calculatorRootController.setSelectedTab(0);
    }

    @FXML
    private void showCalculatorDilutionTab() {
        CalculatorRootController calculatorRootController = (CalculatorRootController) new ViewLoader().displayPane(
            "calculator/calculatorRootView.fxml");
        calculatorRootController.setSelectedTab(1);
    }

    @FXML
    private void showCalculatorMeasuresTab() {
        CalculatorRootController calculatorRootController = (CalculatorRootController) new ViewLoader().displayPane(
            "calculator/calculatorRootView.fxml");
        calculatorRootController.setSelectedTab(2);
    }

    @FXML
    private void showCalculatorSugarTab() {
        CalculatorRootController calculatorRootController = (CalculatorRootController) new ViewLoader().displayPane(
            "calculator/calculatorRootView.fxml");
        calculatorRootController.setSelectedTab(3);
    }

    @FXML
    private void showCalculatorSgTab() {
        CalculatorRootController calculatorRootController = (CalculatorRootController) new ViewLoader().displayPane(
            "calculator/calculatorRootView.fxml");
        calculatorRootController.setSelectedTab(4);
    }

    @FXML
    private void showInformationRootView() {
        new ViewLoader().displayPane("information/informationRootView.fxml");
    }
}
