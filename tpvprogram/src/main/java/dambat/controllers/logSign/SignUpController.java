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
/**
 * Controller for handling the user signup functionality in the application.
 * This class manages user registration, verifies input, and saves the user data to the database.
 * It also allows navigation to the login page.
 * 
 * Dependencies:
 * - Database connection (DBConnection)
 * - JavaFX components for UI interactions
 */
public class SignUpController {

    /**
     * Text field for entering the desired username.
     */
    @FXML
    private TextField usernameField;

    /**
     * Password field for entering the desired password.
     */
    @FXML
    private PasswordField passwordField;

    /**
     * Password field for confirming the entered password.
     */
    @FXML
    private PasswordField confirmPasswordField;

    /**
     * Button for submitting the signup form.
     */
    @FXML
    private Button signupButton;

    /**
     * Label for displaying signup status messages to the user.
     */
    @FXML
    private Label signupStatusLabel;

    /**
     * Button for navigating to the login page.
     */
    @FXML
    private Button loginLink;

    /**
     * Initializes the signup controller.
     * Sets up the actions for the signup button and login link.
     */
    @FXML
    public void initialize() {

        // Configure the login link to navigate to the login page.
        loginLink.setOnAction(event -> {
            try {
                Main.setRoot("Login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Configure the signup button to handle user registration.
        signupButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            // Validate that the passwords match.
            if (!password.equals(confirmPassword)) {
                signupStatusLabel.setText("Passwords do not match!");
                return;
            }

            // Save the new user to the database.
            try (Connection conn = DBConnection.connect()) {
                String query = "INSERT INTO users (username, password) VALUES (?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.executeUpdate();
                signupStatusLabel.setText("Sign up successful!");

                // Navigate to the login page after successful signup.
                Main.setRoot("Login");
            } catch (Exception e) {
                signupStatusLabel.setText("Error: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
