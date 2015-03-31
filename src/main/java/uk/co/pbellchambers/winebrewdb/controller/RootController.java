package uk.co.pbellchambers.winebrewdb.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import uk.co.pbellchambers.winebrewdb.MainApp;
import uk.co.pbellchambers.winebrewdb.controller.calculator.CalculatorRootController;
import uk.co.pbellchambers.winebrewdb.util.ViewLoader;

import java.io.IOException;

public class RootController {

    @FXML
     private void showBrewRootView() {
        try {
            // Load view
            Node node = new ViewLoader().loadAnchorPane("brew/brewRootView.fxml");

            // Set welcome view into the center of root layout.
            MainApp.setDisplayView(node);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showLedgerRootView() {
        try {
            // Load view
            Node node = new ViewLoader().loadAnchorPane("ledger/ledgerRootView.fxml");

            // Set welcome view into the center of root layout.
            MainApp.setDisplayView(node);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showRecipeRootView() {
        try {
            // Load view
            Node node = new ViewLoader().loadAnchorPane("recipe/recipeRootView.fxml");

            // Set welcome view into the center of root layout.
            MainApp.setDisplayView(node);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showCalculatorAlcoholTab() {
        try {
            // Load view
            Node node = new ViewLoader().loadAnchorPane("calculator/calculatorRootView.fxml");

            // Set welcome view into the center of root layout.
            MainApp.setDisplayView(node);
            new CalculatorRootController().setSelectedTab(1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showCalculatorDilutionTab() {
        try {
            // Load view
            Node node = new ViewLoader().loadAnchorPane("calculator/calculatorRootView.fxml");

            // Set welcome view into the center of root layout.
            MainApp.setDisplayView(node);
            new CalculatorRootController().setSelectedTab(1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showCalculatorMeasuresTab() {
        try {
            // Load view
            Node node = new ViewLoader().loadAnchorPane("calculator/calculatorRootView.fxml");

            // Set welcome view into the center of root layout.
            MainApp.setDisplayView(node);
            new CalculatorRootController().setSelectedTab(2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showCalculatorSugarTab() {
        try {
            // Load view
            Node node = new ViewLoader().loadAnchorPane("calculator/calculatorRootView.fxml");

            // Set welcome view into the center of root layout.
            MainApp.setDisplayView(node);
            new CalculatorRootController().setSelectedTab(3);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showCalculatorSgTab() {
        try {
            // Load view
            Node node = new ViewLoader().loadAnchorPane("calculator/calculatorRootView.fxml");

            // Set welcome view into the center of root layout.
            MainApp.setDisplayView(node);
            new CalculatorRootController().setSelectedTab(4);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showInformationRootView() {
        try {
            // Load view
            Node node = new ViewLoader().loadAnchorPane("information/informationRootView.fxml");

            // Set welcome view into the center of root layout.
            MainApp.setDisplayView(node);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
