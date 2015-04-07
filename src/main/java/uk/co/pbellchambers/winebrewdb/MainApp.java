package uk.co.pbellchambers.winebrewdb;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import uk.co.pbellchambers.winebrewdb.controller.RootController;
import uk.co.pbellchambers.winebrewdb.util.Config;
import uk.co.pbellchambers.winebrewdb.util.ViewLoader;

public class MainApp extends Application {

    private static MainApp instance;
    private String wineBrewDBVersion = MainApp.class.getPackage().getImplementationVersion();
    private Config config;
    private Stage primaryStage;
    private BorderPane rootLayout;
    private RootController rootController;

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

        initRootLayout();
        loadConfig();
        setTitle();
        setIcons();
        showWelcomeView();
    }

    /**
     * Loads the config from the ini file
     */
    private void loadConfig() {
        config = new Config();
    }

    /**
     * Set the title of the main window
     */
    public void setTitle() {
        this.primaryStage.setTitle(
            "WineBrewDB " + wineBrewDBVersion + " - Current Database: " + config.getDatabaseLocation());
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
        rootLayout = new ViewLoader().loadRootLayout("rootLayout.fxml");

        // Show the scene containing the root layout.
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Shows the welcome view inside the root layout.
     */
    private void showWelcomeView() {
        new ViewLoader().displayPane("welcomeView.fxml");
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

    /**
     * Gets the primary stage
     *
     * @return primary stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Gets the WineBrewDB version
     *
     * @return wineBrewDBVersion
     */
    public String getWineBrewDBVersion() {
        return wineBrewDBVersion;
    }

    /**
     * Gets the current config
     *
     * @return config
     */
    public Config getConfig() {
        return config;
    }

    /**
     * Gets the root controller
     *
     * @return RootController
     */
    public RootController getRootController() {
        return rootController;
    }

    /**
     * Sets the root controller
     *
     * @param rootController the RootController
     */
    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }
}