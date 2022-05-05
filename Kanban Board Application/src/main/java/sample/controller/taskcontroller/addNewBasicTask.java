package sample.controller.taskcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.controller.projectcontroller.editProject;
import sample.model.object.taskmanagement.basicTask;

import java.io.IOException;

public class addNewBasicTask {

    @FXML
    private Button cancelBtn;
    @FXML
    private Button okBtn;
    @FXML
    private TextArea DescriptionField;
    @FXML
    private TextField TaskNameField;


    public void cancelBtnClick(ActionEvent e){
        Stage stage= (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void okBtnClick(ActionEvent e) throws IOException {

        String description = TaskNameField.getText();
        String name = DescriptionField.getText();
        if (name.isBlank() || description.isBlank())
            return;
        if (editProject.EditProject.EditAddTask){
            int proid = editProject.EditProject.ProjectID;
            int colid = editProject.EditProject.ColumnID;
            int taskid = editProject.EditProject.TaskID;

            basicTask task = editProject.EditProject.ProjectModel.getProjects().get(proid).getProjectColumns().get(colid).getBasicTasks().get(taskid);
            task.editTask(name,description);
            editProject.EditProject.init();
        }
        else{
            int proid = editProject.EditProject.ProjectID;
            int colid = editProject.EditProject.ColumnID;
            int number= editProject.EditProject.ProjectModel.getBasicTaskNumber();
            basicTask task = new basicTask(name,description,number,false);

            editProject.EditProject.ProjectModel.getProjects().get(proid).getProjectColumns().get(colid).addBasicTasK(task,proid,colid);
            editProject.EditProject.ProjectModel.getBasicTasks().add(task);
            editProject.EditProject.LoadProjects();
        }

        Stage stage= (Stage) okBtn.getScene().getWindow();
        stage.close();
    }
}
