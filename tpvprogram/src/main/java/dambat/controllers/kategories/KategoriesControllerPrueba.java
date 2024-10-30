package dambat.controllers.kategories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import dambat.Main;
import dambat.controllers.products.ProductsController;
import dambat.models.Kategories;
import dambat.models.connections.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class KategoriesControllerPrueba {

    @FXML
    private GridPane kategoriesContainer;

    private ObservableList<Kategories> kategoriesList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        loadKategories();
    }

    private void loadKategories() {
        try (Connection conn = DBConnection.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT id_kategoria, kategoria_izena FROM kategoriak")) {

            int row = 0;
            int col = 0;
            while (rs.next()) {
                int id = rs.getInt("id_kategoria");
                String name = rs.getString("kategoria_izena");

                Button kategoryButton = new Button(name);
                kategoryButton.setId(String.valueOf(id));
                kategoryButton.setPrefSize(300, 100);
                try {
                    String imagePath = "/images/" + name.toLowerCase() + ".jpg";
                    Image image = new Image(getClass().getResourceAsStream(imagePath));
                    ImageView imageView = new ImageView(image);

                    imageView.setFitWidth(40);
                    imageView.setFitHeight(40);

                    kategoryButton.setGraphic(imageView);
                } catch (Exception e) {
                    System.out.println(e.getMessage() + " la imagen no puede cargarse");
                }

                kategoryButton.setOnAction(event -> {
                    try {
                        switchToProducts(id);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                kategoriesContainer.add(kategoryButton, col, row);

                col++;
                if (col == 3) {
                    col = 0;
                    row++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void switchToProducts(int id) throws IOException {
        ProductsController.setCategoryId(id);
        Main.setRoot("Product");
    }
}
