package sample.controller.usercontroller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.model.object.user.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import sample.model.registerModel;


public class registerController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button ProfilePicChooseButton;
    @FXML
    private Button ResetButton;
    @FXML
    private Button NewRegisterButton;
    @FXML
    private Button BackToLoginButton;
    @FXML
    private Label RegisterMessage;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField FirstNameField;
    @FXML
    private TextField LastNameField;
    @FXML
    private ImageView lockImage;
    @FXML
    private ImageView ProfileImage;

    private Image defaultImg = null;
    private String imagePath = "";
    public void resetButtonOnAction(ActionEvent e){
        usernameField.clear();
        passwordField.clear();
        FirstNameField.clear();
        LastNameField.clear();
        ProfileImage.setImage(defaultImg);
    }
    public void userAdd(){
        user newUser= new user(FirstNameField.getText(),
                LastNameField.getText(),usernameField.getText(),
                passwordField.getText(),"",new ArrayList<Integer>());
    }
    public void setRegistratrationButton(ActionEvent e){
        registerModel model = new registerModel();

        model.setUsername(usernameField.getText());
        model.setFirstname(FirstNameField.getText());
        model.setLastname(LastNameField.getText());
        model.setPassword(passwordField.getText());
        model.setAvatar(imagePath);

        if (!model.validate())
            RegisterMessage.setText("Please input correct information");

        if (model.signUp())
            RegisterMessage.setText("Register successful, Try login now");
        else
            RegisterMessage.setText("Register Failed, Try again");
    }
    public void switchToLogin(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/sample/login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void ProfilePictureSelector(){

    }

    public void displayImage(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpeg"),
                new FileChooser.ExtensionFilter("Image Files", "*.png"),
                new FileChooser.ExtensionFilter("Image Files", "*.jpg")
        );
        try{
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null){
                imagePath = selectedFile.getAbsolutePath();
                FileInputStream inputstream = new FileInputStream(selectedFile);
                Image image = new Image(inputstream);
                ProfileImage.setImage(image);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        defaultImg = ProfileImage.getImage();
    }
}

