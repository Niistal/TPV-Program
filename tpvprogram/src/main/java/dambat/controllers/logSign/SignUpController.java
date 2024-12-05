package dambat.controllers.logSign;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import dambat.Main;
import dambat.models.connections.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Button signupButton;
    @FXML
    private Label signupStatusLabel;
    @FXML
    private Button loginLink;

    @FXML
    public void initialize() {
        
          
        loginLink.setOnAction(event -> {
            try {
                Main.setRoot("Login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        signupButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();
            
            if (!password.equals(confirmPassword)) {
                signupStatusLabel.setText("Passwords do not match!");
                return;
            }
          
            try (Connection conn = DBConnection.connect()) {
                String query = "INSERT INTO users (username, password) VALUES (?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.executeUpdate();
                signupStatusLabel.setText("Sign up successful!");
                Main.setRoot("Login");
            } catch (Exception e) {
                signupStatusLabel.setText("Error: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}

