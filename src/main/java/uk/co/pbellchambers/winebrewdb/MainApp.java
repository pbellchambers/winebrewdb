package uk.co.pbellchambers.winebrewdb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import uk.co.pbellchambers.winebrewdb.util.ViewLoader;

import java.io.IOException;

public class MainApp extends Application {

    private String WineBrewDBVersion = MainApp.class.getPackage().getImplementationVersion();
    private String DatabaseLocationFromIni = "TODO";
    private Stage primaryStage;
    private static BorderPane rootLayout;

    /**
     * Constructor
     */
    public MainApp() {

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        setTitle();
        setIcons();
        initRootLayout();
        showWelcomeView();
    }

    private void setTitle() {
        this.primaryStage.setTitle(
            "WineBrewDB " + WineBrewDBVersion + " - Current Database: " + DatabaseLocationFromIni);
    }

    private void setIcons() {
        this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/winebrewdb32.png")));
    }

    /**
     * Initializes the root layout.
     */
    private void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();

            rootLayout = new ViewLoader().loadBorderPane("RootLayout.fxml");

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the welcome view inside the root layout.
     */
    private void showWelcomeView() {
        try {
            // Load view
            Node welcomeView = new ViewLoader().loadAnchorPane("welcomeView.fxml");

            // Set welcome view into the center of root layout.
            setDisplayView(welcomeView);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setDisplayView(Node node) {
        rootLayout.setCenter(node);
    }

}