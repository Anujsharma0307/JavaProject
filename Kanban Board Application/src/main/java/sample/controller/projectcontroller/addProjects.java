package sample.controller.projectcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.object.project.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class addProjects implements Initializable {
    @FXML
    private TextField ProjectNameField;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button okBtn;

    public void okBtnClick(ActionEvent e) throws IOException {
        String projectName = ProjectNameField.getText();
        if (!projectName.isBlank()) {
            if (editProject.EditProject.getCreateProjectStatus()){
                int projectID = editProject.EditProject.ProjectModel.getProjectsNumber();
                project pro = new project(projectID,projectName);

                editProject.EditProject.ProjectModel.addProject(pro);
                editProject.EditProject.LoadProjects();
            }else{
                int projectID = editProject.EditProject.getSelectedProjectID();
                editProject.EditProject.ProjectModel.editProject(projectID,projectName);
                editProject.EditProject.LoadProjects();
            }
        }
        Stage stage= (Stage) okBtn.getScene().getWindow();
        stage.close();
    }
    public void cancelBtnClick(ActionEvent e){
        Stage stage= (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProjectNameField.setText("New Project");
    }
}
