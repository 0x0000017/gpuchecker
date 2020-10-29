
package Main;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginController {

    @FXML
    private Button loginButton;
    @FXML
    private Label loginLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label loginStatus;
    @FXML
    private void login(ActionEvent event) throws IOException {
        
        if (username.getText().equals("admin") && password.getText().equals("admin")) {
            ((Node)(event.getSource())).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            
         } else {
            loginStatus.setText("Failed !");
         } 
        
    }
}
