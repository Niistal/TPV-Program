package dambat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductController {

    @FXML
    private TableView<Product> tableProducts;
    @FXML
    private TableColumn<Product, String> colName;
    @FXML
    private TableColumn<Product, Double> colPrice;

    private ObservableList<Product> productsList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        database();
    }

    private void database() {
        try (Connection conn = DBConnection.connect(); 
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT produktua_izena, prezioa FROM produktuak")) {

            while (rs.next()) {
                productsList.add(new Product(rs.getString("produktua_izena"), rs.getDouble("prezioa")));
            }
            tableProducts.setItems(productsList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
