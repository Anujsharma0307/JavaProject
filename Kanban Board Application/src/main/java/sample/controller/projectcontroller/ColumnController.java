package sample.controller.projectcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ColumnController {

    public void addBasicTaskBtn(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddNewBasicTask.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Add basic task");
        stage.show();
    }
    public void addAdvanceTaskBtn(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddNewAdvanceTask.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Add basic task");
        stage.show();
    }
}
