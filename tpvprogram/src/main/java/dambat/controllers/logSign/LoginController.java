package dambat.controllers.logSign;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import dambat.Main;
import dambat.models.connections.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * Controller for handling login functionality in the application. Manages user
 * authentication, navigation to the signup and categories pages, and displays
 * login status messages.
 *
 * Dependencies: - Database connection (DBConnection) - JavaFX components for UI
 * interactions
 */
public class LoginController {

    /**
     * Text field for entering the username.
     */
    @FXML
    private TextField usernameField;
    /**
     * Password field for entering the password.
     */
    @FXML
    private PasswordField passwordField;
    /**
     * Button for submitting the login form.
     */
    @FXML
    private Button loginButton;
    /**
     * Label for displaying login status messages to the user.
     */
    @FXML
    private Label loginStatusLabel;
    /**
     * Button for navigating to the signup page.
     */
    @FXML
    private Button signupLink;
    /**
     * ImageView for displaying an image (optional).
     */
    @FXML
    private ImageView image;

    /**
     * Initializes the login controller. Sets up the login button action, which
     * checks user credentials against the database. Sets up the signup link to
     * navigate to the signup page.
     */
    @FXML
    public void initialize() {
        try (Connection conn = DBConnection.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT username, password FROM users")) {
            // Iterate through user records in the database.
            while (rs.next()) {
                String name = rs.getString("username");
                String password = rs.getString("password");

                // Configure the login button action.
                loginButton.setOnAction(event
                        -> {
                    String username = usernameField.getText();
                    String passwords = passwordField.getText();

                    if (name.equals(username) && password.equals(passwords)) {
                        loginStatusLabel.setText("Login successful!");
                        try {
                            Main.setRoot("kategories");
                        } catch (IOException e) {
                            System.err.println(e.getMessage());

                        }
                    } else {
                        System.out.println("username: " + username + " password: " + password);
                        loginStatusLabel.setText("Invalid credentials!");
                    }
                }
                );
            }
            // Configure the signup link to navigate to the signup page.
            signupLink.setOnAction(event -> {
                try {
                    Main.setRoot("SignUp");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
