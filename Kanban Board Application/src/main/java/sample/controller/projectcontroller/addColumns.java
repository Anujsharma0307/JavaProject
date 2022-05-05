package sample.controller.projectcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.object.project.projectColumn;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class addColumns implements Initializable {
    @FXML
    private Button cancelBtn;
    @FXML
    private Button okBtn;

    @FXML
    private TextField ColumnNameField;

    public void cancelBtnClick(ActionEvent e){
        Stage stage= (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void okBtnClick(ActionEvent e) throws IOException {
        String column = ColumnNameField.getText();
        if (!column.isBlank()){
            int projectID = editProject.EditProject.getSelectedProjectID();
            int columnID = editProject.EditProject.ProjectModel.getProjectColumnNumber();
            projectColumn projectcolumn = new projectColumn(columnID,column);
            editProject.EditProject.ProjectModel.addColumn(projectID,projectcolumn);

            editProject.EditProject.LoadColumns();
        }
        Stage stage= (Stage) okBtn.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ColumnNameField.setText("New Column");
    }
}
