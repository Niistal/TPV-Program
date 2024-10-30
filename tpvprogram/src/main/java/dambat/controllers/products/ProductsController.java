package dambat.controllers.products;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import dambat.Main;
import dambat.models.Product;
import dambat.models.connections.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductsController {

    @FXML
    private static int categoryId;

    @FXML
    private TableView<Product> productstable;
    @FXML
    private TableColumn<Product, Integer> colIdProducto;
    @FXML
    private TableColumn<Product, String> colNombre;
    @FXML
    private TableColumn<Product, Double> colPrecio;
    @FXML
    private TableColumn<Product, Integer> colIdKategoria;

    private ObservableList<Product> productsList = FXCollections.observableArrayList();

    public static void setCategoryId(int id) {
        categoryId = id;
    }

    @FXML
    public void initialize() {

        colIdProducto.setCellValueFactory(new PropertyValueFactory<>("id_prod"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colIdKategoria.setCellValueFactory(new PropertyValueFactory<>("idKategoria"));

        loadProducts();
    }

    private void loadProducts() {
        productsList.clear();
        String query = "SELECT id_produktua, produktua_izena, prezioa, id_kategoria FROM produktuak WHERE id_kategoria = " + categoryId;

        try (Connection conn = DBConnection.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                productsList.add(new Product(
                        rs.getInt("id_produktua"),
                        rs.getString("produktua_izena"),
                        rs.getDouble("prezioa"),
                        rs.getInt("id_kategoria")));
            }
            productstable.setItems(productsList);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void back() {
        try {
            Main.setRoot("Kategoriesprueba");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
