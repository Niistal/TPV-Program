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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ProductsController {

    private static int categoryId;

    @FXML
    private GridPane productsContainer;

    private ObservableList<Product> productsList = FXCollections.observableArrayList();
    @FXML
    private Button one;
    @FXML
    private Button two;
    @FXML
    private Button three;
    @FXML
    private Button four;
    @FXML
    private Button five;
    @FXML
    private Button six;
    @FXML
    private Button seven;
    @FXML
    private Button eight;
    @FXML
    private Button nine;
    @FXML
    private Button cero;
    @FXML
    private Button berdin;
    @FXML
    private Button bider;
    @FXML
    private TextField eragiketa;
    @FXML
    private TextField result;
    
    public static void setCategoryId(int id) {
        categoryId = id;
    }

    @FXML
    public void initialize() {
        loadProducts();
    }

    private void loadProducts() {
        productsList.clear();

        String query = "SELECT id_produktua, produktua_izena, prezioa, id_kategoria FROM produktuak WHERE id_kategoria = " + categoryId;

        try (Connection conn = DBConnection.connect(); 
        Statement stmt = conn.createStatement(); 
        ResultSet rs = stmt.executeQuery(query)) {

            int row = 0;
            int col = 0;

            while (rs.next()) {
                int productId = rs.getInt("id_produktua");
                String name = rs.getString("produktua_izena");
                double price = rs.getDouble("prezioa");
                int categoryId = rs.getInt("id_kategoria");

                Button productButton = new Button(/*  name + "\n \u20AC" + price + " \n" + "IDkategoria: " + categoryId*/);
                productButton.setPrefSize(300, 100);
               try {
                   
                String imagePath = getClass().getResource("/images/" + name.toLowerCase() + ".jpg").toExternalForm();

                 productButton.setStyle("-fx-background-position: center center; "
                        + "-fx-text-fill: white; "
                        + "-fx-font-size: 18px; "
                        + "-fx-padding: 10px;"
                        + "-fx-border-color: grey;"
                        + "-fx-background-color:darkslategray; "
                        + "-fx-background-size: cover ; "
                        + "-fx-background-image: url('" + imagePath + "');"
                        + "-fx-font-weight: bolder;");
                    } catch (Exception e) {
                        productButton.setStyle("-fx-background-position: center center; "
                        + "-fx-text-fill: white; "
                        + "-fx-font-size: 18px; "
                        + "-fx-padding: 10px;"
                        + "-fx-border-color: grey;"
                        + "-fx-background-color:darkslategray; "
                        + "-fx-background-size: cover ; "
                        
                        + "-fx-font-weight: bolder;");
                        System.out.println(e);
                    }

                productButton.setOnAction(event -> {
                    //euroa importatuko da unikodearekin 

                    System.out.println("produktua: " + name + " -"
                            + //
                            " \u20AC " + price);
                    eragiketa.setText(eragiketa.getText() + name  +" , "+ "\n \u20AC  \t "+ price  );

                });

                productsContainer.add(productButton, col, row);

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
    

    @FXML
    private void back() {
        try {
            Main.setRoot("Kategories");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void one() {
        eragiketa.setText(eragiketa.getText() + "1");

    }
    @FXML
    private void two() {
        eragiketa.setText(eragiketa.getText() + "2");
    }
    @FXML    
    private void three() {
        eragiketa.setText(eragiketa.getText() + "3");
    }
    @FXML
    private void four() {
        eragiketa.setText(eragiketa.getText() + "4");
    }
    @FXML
    private void five() {
        eragiketa.setText(eragiketa.getText() + "5");
    }
    @FXML
    private void six() {
        eragiketa.setText(eragiketa.getText() + "6");
    }
    @FXML
    private void seven() {
        eragiketa.setText(eragiketa.getText() + "7");
    }
    @FXML
    private void eight() {
        eragiketa.setText(eragiketa.getText() + "8");
    }
    @FXML
    private void nine() {
        eragiketa.setText(eragiketa.getText() + "9");
    }
    @FXML
    private void zero() {
        eragiketa.setText(eragiketa.getText() + "0");
    }    
    @FXML
    private void bider() {
        eragiketa.setText(eragiketa.getText() + "X");
    }
    @FXML
    private void berdin() {
        result.setText(eragiketa.getText()  ) ;
    }
    @FXML
    private void backspace() {
        eragiketa.setText(eragiketa.getText().substring(0, eragiketa.getText().length() - 1));
    }

    public void setEragiketa(String eragiketa) {
        this.eragiketa.setText(eragiketa);
    }
    

}
