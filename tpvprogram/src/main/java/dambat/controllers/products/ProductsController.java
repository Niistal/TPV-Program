package dambat.controllers.products;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dambat.Main;
import dambat.models.Product;
import dambat.models.connections.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ProductsController {

    private static int guztira = 0;
    private static int categoryId;
    private static int productId;

    @FXML
    private GridPane productsContainer;
    private ObservableList<String[]> orderDetails = FXCollections.observableArrayList();
    private ObservableList<String[]> orderedlist = FXCollections.observableArrayList();

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
    private TextArea result;

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

        try (Connection conn = DBConnection.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            int row = 0;
            int col = 0;

            while (rs.next()) {
                productId = rs.getInt("id_produktua");
                String name = rs.getString("produktua_izena");
                double price = rs.getDouble("prezioa");
                categoryId = rs.getInt("id_kategoria");

                Button productButton = new Button();
                productButton.setPrefSize(300, 100);
                try {

                    String imagePath = getClass().getResource("/images/" + name.toLowerCase() + ".jpg").toExternalForm();

                    productButton.setStyle("-fx-background-position: center center; "
                            + "-fx-text-fill: white; "
                            + "-fx-padding: 10px;"
                            + "-fx-border-color: grey;"
                            + "-fx-background-size: cover ; "
                            + "-fx-background-image: url('" + imagePath + "');"
                    );
                } catch (Exception e) {
                    productButton.setStyle("-fx-background-position: center center; "
                            + "-fx-padding: 10px;"
                            + "-fx-background-size: cover ; "
                            + "-fx-border-color: grey;");

                    System.out.println(e);
                }

                productButton.setOnAction(event -> {
                    //euroa importatuko da unikodearekin 

                    System.out.println("produktua: " + name + " -" + "\n \t\t\t \u20AC " + price);
                    eragiketa.setText(eragiketa.getText() + name + " , " + price + " "/*+  /"\n\t\t\u20AC\t"*/);

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
    private void backspace() {
        eragiketa.setText(eragiketa.getText().substring(0, eragiketa.getText().length() - 1));
    }

    public void setEragiketa(String eragiketa) {
        this.eragiketa.setText(eragiketa);
    }

    @FXML
    private void berdin() {
        if (eragiketa.getText().contains("X")) {

            String eragiketaString = eragiketa.getText().split(",")[1];
            System.out.println(eragiketaString);
            String precio = eragiketaString.split("X")[0];
            String cantidad = eragiketaString.split("X")[1];
            double precioInt = Double.valueOf(precio);
            int cantidadInt = Integer.valueOf(cantidad);
            double results = precioInt * cantidadInt;


            
            guztira += results;

            orderedlist.add(new String[]{String.valueOf(eragiketa.getText().replace(String.valueOf("X"), String.valueOf('*')) + " = " + results + "\n")});
            result.clear();
            result.appendText("Total: " + guztira + "€\n");
            for (String[] eragiketalist : orderedlist) {
                result.appendText(eragiketalist[0]);
            }
            if (!(eragiketa.getText().equals(null))) {
                
                eragiketa.clear();
            } else {
                System.out.println(" the text is null");
            }
            
            orderDetails.add(new String[]{String.valueOf(productId), String.valueOf(cantidadInt), String.valueOf(results)});
        }
        /* 
        if (!(eragiketa.getText().equals(null))) {
            result.appendText(eragiketa.getText() + "€ ");
            result.appendText("\n");

            eragiketa.clear();
        } else {
            System.out.println(" the text is null");
        }*/

    }

    @FXML
    private void send() {
        if (orderDetails.isEmpty()) {
            System.out.println("No hay datos para enviar.");
            return;
        }

        try (Connection conn = DBConnection.connect()) {
            String query = "INSERT INTO eskaera_xehetasunak (id_produktua, kantitatea, azpitotala) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);

            for (String[] list : orderDetails) {
                stmt.setInt(1, Integer.parseInt(list[0]));
                stmt.setInt(2, Integer.parseInt(list[1]));
                stmt.setDouble(3, Double.parseDouble(list[2]));
                stmt.addBatch();
            }

            stmt.executeBatch();
            orderDetails.clear();
            result.clear();
            result.setText("Pedido enviado exitosamente. Total: " + guztira + "€\n");
            guztira = 0;
        } catch (SQLException ex) {
            System.err.println("Error al enviar los datos: " + ex.getMessage());
        }
    }

    @FXML
    private void clear1() {
        eragiketa.clear();
    }

    @FXML
    private void clear2() {
        result.clear();
    }

}
