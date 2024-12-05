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
public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginStatusLabel;
    @FXML
    private Button signupLink;
    @FXML
    private ImageView image;

    @FXML
    public void initialize() {
        try (Connection conn = DBConnection.connect();
         Statement stmt = conn.createStatement(); 
         ResultSet rs = stmt.executeQuery("SELECT username, password FROM users")) {
       while (rs.next()) {
            String name = rs.getString("username");
            String password = rs.getString("password");
           // System.out.println("username: " + name + " password: " + password);
            loginButton.setOnAction (event 
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
                } else  {
                    System.out.println("username: " + username + " password: " + password);
                    loginStatusLabel.setText("Invalid credentials!");
                }
            }
            
            );
        }
       
       
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
    