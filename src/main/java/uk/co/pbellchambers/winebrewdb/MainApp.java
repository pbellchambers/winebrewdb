package uk.co.pbellchambers.winebrewdb;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import uk.co.pbellchambers.winebrewdb.util.ViewLoader;

import java.io.IOException;

public class MainApp extends Application {

    private static MainApp instance;
    private String WineBrewDBVersion = MainApp.class.getPackage().getImplementationVersion();
    private String DatabaseLocationFromIni = "TODO";
    private Stage primaryStage;
    private BorderPane rootLayout;

    /**
     * Constructor
     */
    public MainApp() {
        instance = this;
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

    /**
     * Set the title of the main window
     */
    private void setTitle() {
        this.primaryStage.setTitle(
            "WineBrewDB " + WineBrewDBVersion + " - Current Database: " + DatabaseLocationFromIni);
    }

    /**
     * Set the icons for the main window
     */
    private void setIcons() {
        this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/winebrewdb32.png")));
    }

    /**
     * Initialises the root layout.
     */
    private void initRootLayout() {
        try {
            rootLayout = new ViewLoader().loadRootPane("RootLayout.fxml");

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
            new ViewLoader().loadPane("welcomeView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the view currently being displayed in the centre node of the root layout
     *
     * @param node The node to be displayed
     */
    public void setDisplayView(Node node) {
        rootLayout.setCenter(node);
    }


    /**
     * Method to get current instance of MainApp
     *
     * @return current instance of MainApp
     */
    public static MainApp getInstance() {
        return instance;
    }

}