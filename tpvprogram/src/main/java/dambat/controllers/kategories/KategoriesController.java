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
import javafx.scene.layout.GridPane;

/**
 * Controller for handling the display and interaction with product categories.
 * This class retrieves category data from the database, displays it in a grid
 * layout, and enables navigation to the products page for a selected category.
 *
 * Dependencies: - Database connection (DBConnection) - JavaFX components for UI
 * interactions - `ProductsController` for managing category selection - `Main`
 * for scene transitions
 */
public class KategoriesController {

    /**
     * Grid container for displaying category buttons.
     */
    @FXML
    private GridPane kategoriesContainer;

    /**
     * Observable list for storing category data retrieved from the database.
     */
    private ObservableList<Kategories> kategoriesList = FXCollections.observableArrayList();

    /**
     * Initializes the controller after the root element has been processed.
     * Loads categories and displays them in the grid container.
     */
    @FXML
    public void initialize() {
        loadKategories();
    }

    /**
     * Loads categories from the database and creates buttons for each category.
     * The buttons are styled with background images (if available) and added to
     * the grid container. On button click, the application transitions to the
     * products page for the selected category.
     */
    private void loadKategories() {
        try (Connection conn = DBConnection.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT id_kategoria, kategoria_izena FROM kategoriak")) {

            int row = 0; // Row position in the grid.
            int col = 0; // Column position in the grid.

            while (rs.next()) {
                int id = rs.getInt("id_kategoria"); // Category ID.
                String name = rs.getString("kategoria_izena"); // Category name.

                // Create a button for the category.
                Button kategoryButton = new Button(name);
                kategoryButton.setId(String.valueOf(id));
                kategoryButton.setPrefSize(300, 100);

                try {
                    // Attempt to load and set a background image for the category button.
                    String imagePath = getClass()
                            .getResource("/images/" + name.toLowerCase() + ".jpg")
                            .toExternalForm();
                    kategoryButton.setStyle(
                            "-fx-background-position: center center; "
                            + "-fx-text-fill: white; "
                            + "-fx-font-size: 18px; "
                            + "-fx-padding: 10px;"
                            + "-fx-border-color: grey;"
                            + "-fx-background-size: cover ; "
                            + "-fx-background-image: url('" + imagePath + "');"
                            + "-fx-font-weight: bolder;");
                } catch (Exception e) {
                    System.out.println(e.getMessage() + name + " la imagen no puede cargarse");
                }

                // Set button action to navigate to the products page.
                kategoryButton.setOnAction(event -> {
                    try {
                        switchToProducts(id);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                // Add the button to the grid container.
                kategoriesContainer.add(kategoryButton, col, row);

                // Update grid positions.
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

    /**
     * Switches to the products page for the selected category.
     *
     * @param id The ID of the selected category.
     * @throws IOException if the FXML file for the products page cannot be
     * loaded.
     */
    private void switchToProducts(int id) throws IOException {
        ProductsController.setCategoryId(id);
        Main.setRoot("Product");
    }
}
