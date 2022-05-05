package sample.controller.projectcontroller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.model.LoginModel;
import sample.model.object.project.project;
import sample.model.object.project.projectColumn;
import sample.model.projectModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class editProject implements Initializable {
    private Stage stage;
    private Scene scene;
    public static editProject EditProject = null;

    @FXML
    private Button profileBtn;

    @FXML
    private Label usernameLabel;

    @FXML
    private ImageView ProfileImage;

    @FXML
    private TabPane projectTabPane;
    @FXML
    private Label MotivationalQuotes;

    public projectModel ProjectModel;

    public int ProjectID = 0;
    public int ColumnID = 0;
    public int TaskID = 0;
    public boolean BasicAndAdvance = false;
    public boolean EditAddTask = false;

    private boolean createProject = false;

    public boolean getCreateProjectStatus(){
        return createProject;
    }
    private int selectedProjectID;
    public int getSelectedProjectID(){
        SingleSelectionModel<Tab> selectionModel = projectTabPane.getSelectionModel();
        int index = selectionModel.getSelectedIndex();
        selectedProjectID = ProjectModel.getProjects().get(index).getProjectID();
        return selectedProjectID;
    }
    public void LoadProjects() throws IOException {

        projectTabPane.getTabs().clear();

        ArrayList<project> projects = ProjectModel.getProjects();
        for(int i = 0;i < projects.size();i++){
            Tab tab = new Tab();
            tab.setText(projects.get(i).getProjectName());
            projectTabPane.getTabs().add(tab);

        }
        LoadColumns();
        ArrayList<String> MotivationQuotes = new ArrayList<>();
        MotivationQuotes.add("Don't be pushed around by the fears in your mind. Be led by the dreams in your heart  ― Roy T. Bennett");
        MotivationQuotes.add("It's not the load that breaks you down, it's the way you carry it.    ― Lou Holtz");
        MotivationQuotes.add("Pursue what catches your heart, not what catches your eyes.    ― Roy T. Bennett");
        MotivationQuotes.add("The way to get started is to quit talking and begin doing. ― Walt Disney");
        Collections.shuffle(MotivationQuotes);
        String todaysQuote=MotivationQuotes.get(1);
        MotivationalQuotes.setText(todaysQuote);
    }
    public void LoadColumns() throws IOException {
        for(int j = 0;j < ProjectModel.getProjects().size();j++){
            Tab tab = projectTabPane.getTabs().get(j);

            project pro = ProjectModel.getProjects().get(j);

            GridPane gridPane =  new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);

            for(int i = 0;i < pro.getProjectColumns().size();i++){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Column.fxml"));
                AnchorPane anchorPane = loader.load();
                AnchorPane temp = (AnchorPane) anchorPane.getChildren().get(0);
                Label columnName = (Label)temp.getChildren().get(0);
                columnName.setText(pro.getProjectColumns().get(i).getColumnName());
                MenuButton menuButton = (MenuButton) temp.getChildren().get(1);
                MenuItem menuBasic = menuButton.getItems().get(0);
                MenuItem menuAdvance = menuButton.getItems().get(1);

                pro.getProjectColumns().get(i).getMenuBasic().add(menuBasic);
                pro.getProjectColumns().get(i).getMenuAdvance().add(menuAdvance);


                menuBasic.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        MenuItem item = (MenuItem) actionEvent.getSource();
                        ProjectID = -1;
                        ColumnID = -1;
                        boolean flag = false;
                        for(int i = 0;i < ProjectModel.getProjects().size();i++){
                            project pro = ProjectModel.getProjects().get(i);

                            for(int j = 0;j < pro.getProjectColumns().size();j++){
                                for(int k = 0;k < pro.getProjectColumns().get(j).getMenuBasic().size();k++){
                                    if (item == pro.getProjectColumns().get(j).getMenuBasic().get(k)){
                                        ProjectID = i;
                                        ColumnID = j;
                                        EditAddTask = false;
                                        flag = false;
                                        break;
                                    }
                                }
                                if (flag)
                                    break;;
                            }
                            if (flag)
                                break;
                        }
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/sample/AddNewBasicTask.fxml"));
                            stage = new Stage();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.setResizable(false);
                            stage.setTitle("Add basic task");
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
                menuAdvance.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        MenuItem item = (MenuItem) actionEvent.getSource();
                        ProjectID = -1;
                        ColumnID = -1;
                        boolean flag = false;
                        for(int i = 0;i < ProjectModel.getProjects().size();i++){
                            project pro = ProjectModel.getProjects().get(i);

                            for(int j = 0;j < pro.getProjectColumns().size();j++){
                                for(int k = 0;k < pro.getProjectColumns().get(j).getMenuAdvance().size();k++){
                                    if (item == pro.getProjectColumns().get(j).getMenuAdvance().get(k)){
                                        ProjectID = i;
                                        ColumnID = j;
                                        EditAddTask = false;
                                        flag = false;
                                        break;
                                    }
                                }
                                if (flag)
                                    break;;
                            }
                            if (flag)
                                break;
                        }
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/sample/AddNewAdvanceTask.fxml"));
                            stage = new Stage();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.setResizable(false);
                            stage.setTitle("Add Advance task");
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                gridPane.add(anchorPane,i,0);
                projectColumn column = pro.getProjectColumns().get(i);
                int k = 0;
                for(k = 0;k < column.getBasicTasks().size();k++){
                    FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/sample/Task.fxml"));
                    AnchorPane anchorPane1 = loader1.load();
                    AnchorPane temp1 = (AnchorPane) anchorPane1.getChildren().get(0);
                    Button editBtn = (Button) temp1.getChildren().get(1);
                    pro.getProjectColumns().get(i).getBasicEditButtons().add(editBtn);

                    editBtn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            Button item = (Button) actionEvent.getSource();
                            ProjectID = -1;
                            ColumnID = -1;
                            TaskID = -1;
                            boolean flag = false;
                            for(int i = 0;i < ProjectModel.getProjects().size();i++){
                                project pro = ProjectModel.getProjects().get(i);

                                for(int j = 0;j < pro.getProjectColumns().size();j++){
                                    for(int k = 0;k < pro.getProjectColumns().get(j).getBasicEditButtons().size();k++){
                                        if (item == pro.getProjectColumns().get(j).getBasicEditButtons().get(k)){
                                            ProjectID = i;
                                            ColumnID = j;
                                            TaskID = k;
                                            BasicAndAdvance = true;
                                            EditAddTask = true;
                                            flag = false;
                                            break;
                                        }
                                    }
                                    if (flag)
                                        break;;
                                }
                                if (flag)
                                    break;
                            }
                            Parent root = null;
                            try {
                                root = FXMLLoader.load(getClass().getResource("/sample/AddNewBasicTask.fxml"));
                                stage = new Stage();
                                scene = new Scene(root);
                                stage.setScene(scene);
                                stage.setResizable(false);
                                stage.setTitle("Edit basic task");
                                stage.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    Button deleteBtn = (Button) temp1.getChildren().get(2);
                    pro.getProjectColumns().get(i).getBasicDeleteButtons().add(deleteBtn);

                    deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            Button item = (Button) actionEvent.getSource();
                            ProjectID = -1;
                            ColumnID = -1;
                            TaskID = -1;
                            boolean flag = false;
                            for(int i = 0;i < ProjectModel.getProjects().size();i++){
                                project pro = ProjectModel.getProjects().get(i);

                                for(int j = 0;j < pro.getProjectColumns().size();j++){
                                    for(int k = 0;k < pro.getProjectColumns().get(j).getBasicDeleteButtons().size();k++){
                                        if (item == pro.getProjectColumns().get(j).getBasicDeleteButtons().get(k)){
                                            ProjectID = i;
                                            ColumnID = j;
                                            TaskID = k;
                                            BasicAndAdvance = true;
                                            flag = false;
                                            break;
                                        }
                                    }
                                    if (flag)
                                        break;;
                                }
                                if (flag)
                                    break;
                            }

                            ProjectModel.deleteBasicTask(ProjectID,ColumnID,TaskID);
                            ProjectModel.LoadProjects();
                            ProjectModel.LoadColumns();
                            ProjectModel.LoadBasicTasks();
                            ProjectModel.LoadAdvanceTasks();
                            try {
                                LoadProjects();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    Label taskName = (Label)temp1.getChildren().get(0);
                    taskName.setText(column.getBasicTasks().get(k).getName());
                    gridPane.add(anchorPane1,i,k + 1);
                }
                for(int l = 0;l < column.getAdvanceTasks().size();l++){
                    FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/sample/Task.fxml"));
                    AnchorPane anchorPane2 = loader2.load();
                    AnchorPane temp2 = (AnchorPane) anchorPane2.getChildren().get(0);

                    Button editBtn = (Button) temp2.getChildren().get(1);
                    pro.getProjectColumns().get(i).getAdvanceEditButtons().add(editBtn);

                    editBtn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            Button item = (Button) actionEvent.getSource();
                            ProjectID = -1;
                            ColumnID = -1;
                            TaskID = -1;
                            boolean flag = false;
                            for(int i = 0;i < ProjectModel.getProjects().size();i++){
                                project pro = ProjectModel.getProjects().get(i);

                                for(int j = 0;j < pro.getProjectColumns().size();j++){
                                    for(int k = 0;k < pro.getProjectColumns().get(j).getAdvanceEditButtons().size();k++){
                                        if (item == pro.getProjectColumns().get(j).getAdvanceEditButtons().get(k)){
                                            ProjectID = i;
                                            ColumnID = j;
                                            TaskID = k;
                                            BasicAndAdvance = false;
                                            EditAddTask = true;
                                            flag = false;
                                            break;
                                        }
                                    }
                                    if (flag)
                                        break;;
                                }
                                if (flag)
                                    break;
                            }
                            Parent root = null;
                            try {
                                root = FXMLLoader.load(getClass().getResource("/sample/AddNewAdvanceTask.fxml"));
                                stage = new Stage();
                                scene = new Scene(root);
                                stage.setScene(scene);
                                stage.setResizable(false);
                                stage.setTitle("Edit advance task");
                                stage.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    Button deleteBtn = (Button) temp2.getChildren().get(2);
                    pro.getProjectColumns().get(i).getAdvanceDeleteButtons().add(deleteBtn);

                    deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            Button item = (Button) actionEvent.getSource();
                            ProjectID = -1;
                            ColumnID = -1;
                            TaskID = -1;
                            boolean flag = false;
                            for(int i = 0;i < ProjectModel.getProjects().size();i++){
                                project pro = ProjectModel.getProjects().get(i);

                                for(int j = 0;j < pro.getProjectColumns().size();j++){
                                    for(int k = 0;k < pro.getProjectColumns().get(j).getAdvanceDeleteButtons().size();k++){
                                        if (item == pro.getProjectColumns().get(j).getAdvanceDeleteButtons().get(k)){
                                            ProjectID = i;
                                            ColumnID = j;
                                            TaskID = k;
                                            BasicAndAdvance = false;
                                            EditAddTask = true;
                                            flag = false;
                                            break;
                                        }
                                    }
                                    if (flag)
                                        break;;
                                }
                                if (flag)
                                    break;
                            }

                            ProjectModel.deleteAdvanceTask(ProjectID,ColumnID,TaskID);
                            ProjectModel.LoadProjects();
                            ProjectModel.LoadColumns();
                            ProjectModel.LoadBasicTasks();
                            ProjectModel.LoadAdvanceTasks();
                            try {
                                LoadProjects();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    Label taskName = (Label)temp2.getChildren().get(0);
                    Label taskDate = (Label)temp2.getChildren().get(3);
                    taskDate.setText(column.getAdvanceTasks().get(l).getDate().toString());
                    taskDate.setStyle("-fx-background-color: #FFBC17;");
                    taskName.setText(column.getAdvanceTasks().get(l).getName());
                    gridPane.add(anchorPane2,i,l + 1 + k);
                }
            }

            tab.setContent(gridPane);
        }
    }
    public void addNewColumn(ActionEvent e) throws IOException {
        EditAddTask = false;
        Parent root = FXMLLoader.load(getClass().getResource("/sample/AddNewColumn.fxml"));
        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Add column to learn java");
        stage.show();
    }
    public void addNewProjectBtnClick(ActionEvent e) throws IOException {
        EditAddTask = false;
        createProject = true;
        Parent root = FXMLLoader.load(getClass().getResource("/sample/CreateProject.fxml"));
        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Create new Project");
        stage.show();
    }
    public void profileBtnClick(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/EditProfile.fxml"));
        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.setOnHiding(windowEvent -> {
            displayAvatar();
        });
    }
    public void logOutClick(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/login.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        LoginModel.clearInfo();
    }

    public void init(){
        EditProject = this;
        ProjectModel = new projectModel();
        if (LoginModel.loginStatus){
            displayAvatar();
        }
        ProjectModel.LoadProjects();
        ProjectModel.LoadColumns();
        ProjectModel.LoadBasicTasks();
        ProjectModel.LoadAdvanceTasks();

        if (ProjectModel.getProjectsNumber() > 0){
            try {
                LoadProjects();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    public void displayAvatar(){
        usernameLabel.setText(LoginModel.getUsername());
        String path = LoginModel.getAvatarImageUrl();
        if (!path.isBlank()){
            try{
                FileInputStream inputstream = new FileInputStream(path);
                Image image = new Image(inputstream);
                ProfileImage.setImage(image);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void deleteProjectBtnClick(ActionEvent e) throws IOException {
        ProjectModel.deleteProject(selectedProjectID);

        ProjectModel = new projectModel();
        if (LoginModel.loginStatus){
            displayAvatar();
        }
        ProjectModel.LoadProjects();
        ProjectModel.LoadColumns();
        ProjectModel.LoadBasicTasks();
        ProjectModel.LoadAdvanceTasks();

        LoadProjects();

    }
    public void renameProjectBtnClick(ActionEvent e) throws IOException {
        createProject = false;
        Parent root = FXMLLoader.load(getClass().getResource("/sample/CreateProject.fxml"));
        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Rename Project");
        stage.show();
    }
}
