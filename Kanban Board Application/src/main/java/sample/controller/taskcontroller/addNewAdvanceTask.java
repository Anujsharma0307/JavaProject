package sample.controller.taskcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.controller.projectcontroller.editProject;
import sample.model.object.taskmanagement.advanceTask;

import java.io.IOException;
import java.time.LocalDate;

public class addNewAdvanceTask {
    @FXML
    private Button cancelBtn;
    @FXML
    private Button okBtn;
    @FXML
    private TextArea DescriptionField;
    @FXML
    private TextField TaskNameField;
    @FXML
    private DatePicker datePicker;

    public void cancelBtnClick(ActionEvent e){
        Stage stage= (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void okBtnClick(ActionEvent e) throws IOException {
        String description = TaskNameField.getText();
        String name = DescriptionField.getText();
        LocalDate localDate = datePicker.getValue();
        if (name.isBlank() || description.isBlank())
            return;

        if (editProject.EditProject.EditAddTask){
            int proid = editProject.EditProject.ProjectID;
            int colid = editProject.EditProject.ColumnID;
            int taskid = editProject.EditProject.TaskID;

            advanceTask task = editProject.EditProject.ProjectModel.getProjects().get(proid).getProjectColumns().get(colid).getAdvanceTasks().get(taskid);
            task.editTask(name,description,localDate);
            editProject.EditProject.init();
        }
        else{
            int proid = editProject.EditProject.ProjectID;
            int colid = editProject.EditProject.ColumnID;
            int number= editProject.EditProject.ProjectModel.getBasicTaskNumber();

            advanceTask task = new advanceTask(name,description,number,false,localDate,new Color(0,0,1,0));

            editProject.EditProject.ProjectModel.getProjects().get(proid).getProjectColumns().get(colid).addAdvanceTasK(task,proid,colid);
            editProject.EditProject.ProjectModel.getAdvanceTasks().add(task);
            editProject.EditProject.LoadProjects();
        }


        Stage stage= (Stage) okBtn.getScene().getWindow();
        stage.close();
    }
}
