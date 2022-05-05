package sample.model.object.project;

import javafx.scene.control.MenuButton;

import java.util.ArrayList;

public class project {
    private int projectID;
    private String projectName;
    private ArrayList<projectColumn> projectColumns;

    public project(int projectID,String projectName){
        this.projectID = projectID;
        this.projectName = projectName;
        projectColumns = new ArrayList<>();
    }
    public project(int projectID, String projectName, int columns) {
        this.projectID = projectID;
        this.projectName = projectName;

    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public ArrayList<projectColumn> getProjectColumns(){
        return projectColumns;
    }
    public void addColumnn(projectColumn column){
        projectColumns.add(column);
    }
    public int getProjectColummnsNumber(){
        return projectColumns.size();
    }
}