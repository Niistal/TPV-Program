package dambat;

import java.io.IOException;

import dambat.controllers.products.ProductSelectSave;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 * Main class for the JavaFX application.
 * This class extends {@link javafx.application.Application} and serves as the entry point.
 */
public class Main extends Application {

    private static Scene scene;

    /**
     * The main entry point for all JavaFX applications. This method is called
     * when the application is launched.
     *
     * @param stage The primary stage for this application, onto which the
     * application scene can be set.
     * @throws Exception if an error occurs during loading the FXML or any other
     * initialization.
     */
    @Override
    public void start(Stage stage) throws Exception {

        scene = new Scene(loadFXML("Login"), 0, 0);

        // Uncomment the next line to load the "Kategories" FXML.
        // scene = new Scene(loadFXML("Kategories"), 0, 0);
        stage.setFullScreen(true);
        ProductSelectSave.saveResultToFile("data/result.txt", "");
        ProductSelectSave.saveResultToFile("data/total.txt", "");
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("TPV - KATEGORIES");
    }

    /**
     * Sets the root of the scene to the given FXML file.
     *
     * @param fxml the name of the FXML file to load.
     * @throws IOException if an error occurs during loading the FXML.
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Loads an FXML file and returns the resulting {@link javafx.scene.Parent}
     * node.
     *
     * @param fxml the name of the FXML file to load.
     * @return the loaded {@link javafx.scene.Parent} node.
     * @throws IOException if an error occurs during loading the FXML.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        Parent root = fxmlLoader.load();
        return root;
    }

    /**
     * The main method for launching the JavaFX application.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
