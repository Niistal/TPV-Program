package dambat.controllers.kategories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import dambat.Main;
import dambat.models.Kategories;
import dambat.models.connections.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class KategoriesController {

    @FXML
    private TableView<Kategories> kategoriestable;
    @FXML
    private TableColumn<Kategories, String> colName;
    @FXML
    private TableColumn<Kategories, Integer> colId;
    @FXML
    private Button myButton;

    private ObservableList<Kategories> kategoriesList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        database();

    }

    @FXML
    private void switchToProducts() throws IOException {
        Main.setRoot("Product");
       
    }

    @FXML
    private void database() {
        try (Connection conn = DBConnection.connect();
         Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery("SELECT id_kategoria, kategoria_izena FROM kategoriak")) {

            while (rs.next()) {
                kategoriesList.add(new Kategories(
                    rs.getInt("id_kategoria"),
                    rs.getString("kategoria_izena")));
            }
            kategoriestable.setItems(kategoriesList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
}
