package dambat;

import java.io.IOException;

import dambat.controllers.products.ProductSelectSave;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("/dambat/Kategories.fxml"));
        //scene = new Scene(loadFXML("Login"), 0, 0);
        //scene = new Scene(loadFXML("Signup"), 0, 0);
        scene = new Scene(loadFXML("Kategories"), 0, 0);
        stage.setFullScreen(true);
        ProductSelectSave.saveResultToFile("data/result.txt", "");
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("TPV - KATEGORIES");

    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        Parent root = fxmlLoader.load();

        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
