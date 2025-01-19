package dambat.controllers.products;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

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

/**
 * Controller for handling product-related operations in the application.
 * Manages product loading, user interactions, order calculations, and PDF
 * generation. This class interacts with the database and UI elements defined in
 * the FXML file.
 *
 * Dependencies: - Database connection (DBConnection) - PDF generation library
 * (iText) - JavaFX components for UI interactions
 */
public class ProductsController {

    /**
     * Total amount of the current order.
     */
    private static int guztira = 0;
    /**
     * The current category ID used for filtering products.
     */
    private static int categoryId;

    @FXML
    private GridPane productsContainer; // Container for displaying product buttons.

    private ObservableList<String[]> orderDetails = FXCollections.observableArrayList();  // Order details list.
    private ObservableList<String[]> orderedlist = FXCollections.observableArrayList(); // Ordered list for display.
    /**
     * Stores the ID of the currently selected product.
     */
    public int id;

    private ObservableList<Product> productsList = FXCollections.observableArrayList();// List of products.

    // UI Elements for user interaction.
    @FXML
    private Button one, two, three, four, five, six, seven, eight, nine, cero, berdin, bider;
    @FXML
    private TextField eragiketa;
    @FXML
    private TextArea result;
    @FXML
    private TextArea total;

    /**
     * Sets the category ID for filtering products.
     *
     * @param id the category ID to be set
     */
    public static void setCategoryId(int id) {
        categoryId = id;
    }

    /**
     * Initializes the controller after the root element has been processed.
     * Loads products and previous results into the UI.
     */
    @FXML
    public void initialize() {
        loadProducts();
        loadResult();

    }

    /**
     * Loads products from the database based on the selected category and
     * displays them in the UI.
     */
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

    /**
     * Appends the numbers 0-9 to the operation text field.
     */
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

    /**
     * Appends the multiplication symbol to the operation text field.
     */
    @FXML
    private void bider() {
        eragiketa.setText(eragiketa.getText() + "X");
    }

    /**
     * Removes the last character from the operation text field.
     */
    @FXML
    private void backspace() {
        eragiketa.setText(eragiketa.getText().substring(0, eragiketa.getText().length() - 1));
    }

    /**
     * Sets the content of the operation text field.
     *
     * @param eragiketa the text to set
     */
    public void setEragiketa(String eragiketa) {
        this.eragiketa.setText(eragiketa);
    }

    /**
     * Performs the calculation based on the operation entered by the user,
     * updates the result, and saves it to the file.
     */
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

    /**
     * Sends the order details to the database and generates a PDF summary.
     */
    @FXML
    private void send() throws SQLException, FileNotFoundException, DocumentException {
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

    /**
     * Clears the operation text field and resets the total amount.
     */
    @FXML
    private void clear1() {

        eragiketa.clear();
        guztira = 0;
    }

    /**
     * Clears all order-related data and resets UI components.
     */
    @FXML
    private void clear2() {
        ProductSelectSave.saveResultToFile("total.txt", "");
        ProductSelectSave.saveResultToFile("result.txt", "");
        total.clear();
        result.clear();
        orderDetails.clear();
    }

    /**
     * Saves the current result and total to files.
     */
    private void saveResult() {
        ProductSelectSave.saveResultToFile("data/result.txt", result.getText());
        ProductSelectSave.saveResultToFile("data/total.txt", total.getText());
    }

    /**
     * Loads the result and total from files and populates the UI.
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
     * Clears the result text area and associated file content.
     */
    @FXML
    private void clearResult() {
        result.clear();
        total.clear();
        ProductSelectSave.saveResultToFile("data/result.txt", "");
        ProductSelectSave.saveResultToFile("data/total.txt", "");

    }

    /**
     * Saves the current result and navigates back to the categories scene.
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

    /**
     * Generates a PDF summary of the current order and opens it automatically.
     */
    private void generatePDF() throws FileNotFoundException, DocumentException {

        String pdfPath = "Pedido.pdf"; // Ruta del archivo PDF
        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream("pedido.pdf"));

        document.open();

        try (PrintWriter writer = new PrintWriter(pdfPath, "UTF-8")) {

            // Calcular el IVA y subtotal
            double iva = guztira * 0.21; // IVA del 21%
            double subtotal = guztira - iva;

            //escribir en el pdf
            System.out.println("PDF generado correctamente: " + pdfPath);
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Font boldFont = FontFactory.getFont(FontFactory.COURIER, 16, Font.BOLD, BaseColor.BLACK);
            Font boldFont2 = FontFactory.getFont(FontFactory.COURIER, 20, Font.BOLD, BaseColor.BLACK);

            Chunk chunk = new Chunk("Resumen del Pedido", boldFont);
            Chunk chunk0 = new Chunk("==================", font);

            Chunk chunk1 = new Chunk("Detalle de Productos:", font);
            String content = result.getText();
            document.add(new Paragraph(chunk));
            document.add(new Paragraph(chunk0));
            document.add(new Paragraph(chunk1));
            document.add(new Paragraph("Resumen del Total:\n", boldFont));
            document.add(new Paragraph(content + "\n", font));

            Chunk chunk3 = new Chunk(String.format("Subtotal (sin IVA): %.2f €\n", subtotal), boldFont);
            Chunk chunk4 = new Chunk(String.format("IVA (21%%): %.2f €\n", iva), font);
            Chunk chunk5 = new Chunk(String.format("Total (con IVA): %.2f €\n", (double) guztira), boldFont2);
            Chunk chunk6 = new Chunk("Gracias por su compra. en Nistaldrinks", font);

            document.add(chunk3);
            document.add(chunk4);
            document.add(chunk5);
            document.add(chunk6);
            document.close();

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
