package uk.co.pbellchambers.winebrewdb.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import uk.co.pbellchambers.winebrewdb.MainApp;
import uk.co.pbellchambers.winebrewdb.controller.calculator.CalculatorRootController;
import uk.co.pbellchambers.winebrewdb.util.UpdateChecker;
import uk.co.pbellchambers.winebrewdb.util.ViewLoader;

public class RootController {

    @FXML
    private Button rootBrewButton;

    @FXML
    private Button rootLedgerButton;

    @FXML
    private Button rootRecipeButton;

    @FXML
    private Button rootCalculatorButton;

    @FXML
    private Button rootInformationButton;

    @FXML
    private MenuItem rootSaveMenuItem;

    @FXML
    private MenuItem rootBrewMenuItem;

    @FXML
    private MenuItem rootLedgerMenuItem;

    @FXML
    private MenuItem rootRecipeMenuItem;

    @FXML
    private MenuItem rootCalculatorAlcoholMenuItem;

    @FXML
    private MenuItem rootCalculatorDilutionMenuItem;

    @FXML
    private MenuItem rootCalculatorMeasuresMenuItem;

    @FXML
    private MenuItem rootCalculatorSugarMenuItem;

    @FXML
    private MenuItem rootCalculatorSgMenuItem;

    @FXML
    private MenuItem rootInformationMenuItem;

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
        new ViewLoader().showModalDialog(Alert.AlertType.INFORMATION, "Credits", "Credits", "modal/creditsView.fxml");
    }

    @FXML
    private void showReportBugsView() {
        new ViewLoader().showModalDialog(Alert.AlertType.INFORMATION, "Report a bug",
                                         "Report a bug",
                                         "modal/reportBugsView.fxml");
    }

    @FXML
    private void checkForUpdates() {
        new UpdateChecker().checkForUpdates();
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

    /**
     * Disables all menu items that require a database connection
     */
    public void disableAll() {
        rootSaveMenuItem.setDisable(true);
        rootBrewMenuItem.setDisable(true);
        rootLedgerMenuItem.setDisable(true);
        rootRecipeMenuItem.setDisable(true);
        rootCalculatorAlcoholMenuItem.setDisable(true);
        rootCalculatorDilutionMenuItem.setDisable(true);
        rootCalculatorMeasuresMenuItem.setDisable(true);
        rootCalculatorSugarMenuItem.setDisable(true);
        rootCalculatorSgMenuItem.setDisable(true);
        rootInformationMenuItem.setDisable(true);
        rootBrewButton.setDisable(true);
        rootLedgerButton.setDisable(true);
        rootRecipeButton.setDisable(true);
        rootCalculatorButton.setDisable(true);
        rootInformationButton.setDisable(true);
    }

    /**
     * Enables all menu items that require a database connection
     */
    public void enableAll() {
        rootSaveMenuItem.setDisable(false);
        rootBrewMenuItem.setDisable(false);
        rootLedgerMenuItem.setDisable(false);
        rootRecipeMenuItem.setDisable(false);
        rootCalculatorAlcoholMenuItem.setDisable(false);
        rootCalculatorDilutionMenuItem.setDisable(false);
        rootCalculatorMeasuresMenuItem.setDisable(false);
        rootCalculatorSugarMenuItem.setDisable(false);
        rootCalculatorSgMenuItem.setDisable(false);
        rootInformationMenuItem.setDisable(false);
        rootBrewButton.setDisable(false);
        rootLedgerButton.setDisable(false);
        rootRecipeButton.setDisable(false);
        rootCalculatorButton.setDisable(false);
        rootInformationButton.setDisable(false);
    }
}
