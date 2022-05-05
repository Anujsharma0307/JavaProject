package sample.controller.projectcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Program;
import sample.model.LoginModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class editProfile implements Initializable {

    @FXML
    private TextField FirstNameField;
    @FXML
    private TextField LastNameField;
    @FXML
    private TextField UsernameField;
    @FXML
    private Button cancelBtn;

    @FXML
    private ImageView ProfileImage;

    private String imagePath = "";
    public editProfile(){

    }


    public void okBtnClick(ActionEvent e){
        LoginModel.setFirstname(FirstNameField.getText());
        LoginModel.setLastname(LastNameField.getText());
        LoginModel.setUsername(UsernameField.getText());
        LoginModel.setAvatarImageUrl(imagePath);
        LoginModel.saveUser();

        Stage stage= (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    public void cancelBtnClick(ActionEvent e) throws IOException {
        Stage stage= (Stage) cancelBtn.getScene().getWindow();
        stage.close();
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
        UsernameField.setText(LoginModel.getUsername());
        FirstNameField.setText(LoginModel.getFirstname());
        LastNameField.setText(LoginModel.getLastname());
        imagePath = LoginModel.getAvatarImageUrl();

        if (!imagePath.isBlank()){
            try{
                FileInputStream inputstream = new FileInputStream(imagePath);
                Image image = new Image(inputstream);
                ProfileImage.setImage(image);
            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }
}
