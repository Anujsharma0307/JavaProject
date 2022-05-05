package sample.controller.usercontroller;

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
import sample.model.LoginModel;

import java.io.IOException;


public class loginController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button cancelButton;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginMessage;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button Register;

    public void setLoginMessage(ActionEvent e){
    }

    public void cancelButtonOnAction(ActionEvent e){
        Stage stage= (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public void setLoginButton(ActionEvent event) throws IOException {
        LoginModel loginModel = new LoginModel(usernameField.getText(),passwordField.getText());
        LoginModel.loginStatus = loginModel.loginUser();
        if (!LoginModel.loginStatus)
            loginMessage.setText("Enter username and password");
        else {
            loginMessage.setText("login successful");
            Parent root = FXMLLoader.load(getClass().getResource("/sample/NewProject.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void validateLogin(){

    }

    public void switchToSignUp(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/sample/SignUp.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }
