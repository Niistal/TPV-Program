package dambat.controllers.products;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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

    @FXML
    private GridPane productsContainer;
    private ObservableList<String[]> orderDetails = FXCollections.observableArrayList();
    private ObservableList<String[]> orderedlist = FXCollections.observableArrayList();
    public int id;

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
    @FXML
    private TextArea total;

    public static void setCategoryId(int id) {
        categoryId = id;
    }

    @FXML
    public void initialize() {
        loadProducts();
        loadResult();

    }

    private void loadProducts() {
        productsList.clear();

        String query = "SELECT id_produktua, produktua_izena, prezioa, id_kategoria FROM produktuak WHERE id_kategoria = " + categoryId;

        try (Connection conn = DBConnection.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            int row = 0;
            int col = 0;

            while (rs.next()) {
                int productId = rs.getInt("id_produktua");
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
                    id = productId;
                    System.out.println("produktua: " + name + " -" + "\n \t\t\t \u20AC " + price + " " + productId);
                    eragiketa.setText(eragiketa.getText() + name + " , " + price);
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
    private void berdin() throws SQLException, IOException {
        if (eragiketa.getText().contains("X")) {
            String eragiketaString = eragiketa.getText().split(",")[1];
            String precio = eragiketaString.split("X")[0].trim();
            String cantidad = eragiketaString.split("X")[1].trim();
            double precioInt = Double.parseDouble(precio);
            int cantidadInt = Integer.parseInt(cantidad);
            
            double results = precioInt * cantidadInt;
            guztira += results;
            
            String newEntry = eragiketa.getText().replace("X", "*") + " = " + results;
            
            // Leer contenido del archivo y comparar
            File file = new File("data/result.txt");
            if (!file.exists()) {
                file.createNewFile(); // Crear archivo si no existe
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            boolean isDuplicate = false; // Verificar si el producto ya existe
            StringBuilder fileContent = new StringBuilder();
            
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append("\n");
                if (line.trim().equals(newEntry.trim())) {
                    isDuplicate = true; // Producto ya existe en el archivo
                }
            }
            reader.close();
            
            if (!isDuplicate) {
                orderedlist.add(new String[]{newEntry + "\n"});
                result.appendText(newEntry + "\n");
            }
            
            total.setText("Total: " + guztira + "€");
            
            ProductSelectSave.saveResultToFile("data/result.txt", result.getText());
            
            eragiketa.clear();
            
            orderDetails.add(new String[]{String.valueOf(id), String.valueOf(cantidadInt), String.valueOf(results)});
        }
    }
    
    @FXML
    private void send() throws SQLException {
        if (orderDetails.isEmpty()) {
            System.out.println("No hay datos para enviar.");
            return;
        }

        try (Connection conn = DBConnection.connect()) {
            conn.setAutoCommit(false);

            String insertOrderQuery = "INSERT INTO eskaerak (eskaera_data, guztira) VALUES (CURRENT_TIMESTAMP, ?)";
            PreparedStatement insertOrderStmt = conn.prepareStatement(insertOrderQuery, Statement.RETURN_GENERATED_KEYS);
            insertOrderStmt.setDouble(1, guztira);
            insertOrderStmt.executeUpdate();

            ResultSet rs = insertOrderStmt.getGeneratedKeys();
            int idEskaera = 0;
            if (rs.next()) {
                idEskaera = rs.getInt(1);
            } else {
                throw new SQLException("No se pudo obtener el id_eskaera.");
            }

            String insertDetailsQuery = "INSERT INTO eskaera_xehetasunak (id_eskaera, id_produktua, kantitatea, azpitotala) VALUES (?, ?, ?, ?)";
            PreparedStatement insertDetailsStmt = conn.prepareStatement(insertDetailsQuery);

            for (String[] list : orderDetails) {
                insertDetailsStmt.setInt(1, idEskaera);
                insertDetailsStmt.setInt(2, Integer.parseInt(list[0]));
                insertDetailsStmt.setInt(3, Integer.parseInt(list[1]));
                insertDetailsStmt.setDouble(4, Double.parseDouble(list[2]));
                insertDetailsStmt.addBatch();
            }

            insertDetailsStmt.executeBatch();
            conn.commit();
            generatePDF();
            result.clear();
            orderDetails.clear();
            orderDetails.removeAll(orderDetails);

            result.setText("Pedido enviado exitosamente. Total: " + guztira + "€\n");
            guztira = 0;

        } catch (SQLException ex) {
            System.err.println("Error al enviar los datos: " + ex.getMessage());
        }
    }
    @FXML
    private void clear1() {

        eragiketa.clear();
        guztira = 0;
    }

    @FXML
    private void clear2() {
        ProductSelectSave.saveResultToFile("total.txt", "");
        ProductSelectSave.saveResultToFile("result.txt", "");
        total.clear();
        result.clear();
        orderDetails.clear();
    }

    /**
     * Guardar el contenido actual del TextArea en el archivo `result.txt` y
     * `total.txt`.
     */
    private void saveResult() {
        ProductSelectSave.saveResultToFile("data/result.txt", result.getText());
        ProductSelectSave.saveResultToFile("data/total.txt", total.getText());
    }

    /**
     * Cargar el contenido existente del archivo `result.txt` al TextArea.
     */
    private void loadResult() {
        String content = ProductSelectSave.loadResultFromFile("data/result.txt");
        String contentt = ProductSelectSave.loadResultFromFile("data/total.txt");

        if (content != null && !content.isEmpty()) {
            result.appendText(content); // Establece el contenido en el TextArea
        }
        if (contentt != null && !contentt.isEmpty()) {
            total.setText(contentt); // Establece el contenido en el TextArea
        }
    }

    /**
     * Limpia el contenido del TextArea y del archivo asociado.
     */
    @FXML
    private void clearResult() {
        result.clear();
        total.clear();
        ProductSelectSave.saveResultToFile("data/result.txt", "");
        ProductSelectSave.saveResultToFile("data/total.txt", "");

    }

    /**
     * Botón "Volver" para regresar a la escena de categorías. Guarda el
     * contenido actual antes de salir.
     */
    @FXML
    private void back() {
        saveResult(); // Guardar contenido antes de cambiar de escena
        try {
            Main.setRoot("Kategories");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generatePDF() {
        String pdfPath = "Pedido.pdf"; // Ruta del archivo PDF

        try (PrintWriter writer = new PrintWriter(pdfPath, "UTF-8")) {

           
            writer.println("Resumen del Pedido");
            writer.println("==================");
            writer.println();

        
            writer.println("Detalle de Productos:");

            writer.println(result.getText());
            writer.println();

            // Calcular el IVA y subtotal
            double iva = guztira * 0.21; // IVA del 21%
            double subtotal = guztira - iva;

            writer.println("Resumen del Total:");
            writer.printf("Subtotal (sin IVA): %.2f €%n", subtotal);
            writer.printf("IVA (21%%): %.2f €%n", iva); // Escapar el símbolo '%'
            writer.printf("Total (con IVA): %.2f €%n", (double) guztira);

            writer.println();
            writer.println("Gracias por su compra.");

            System.out.println("PDF generado correctamente: " + pdfPath);

            // Abrir automáticamente el PDF generado (opcional)
            File pdfFile = new File(pdfPath);
            if (pdfFile.exists()) {
                java.awt.Desktop.getDesktop().open(pdfFile);
            }

        } catch (IOException e) {
            System.err.println("Error al generar el PDF: " + e.getMessage());
        }
    }

}
