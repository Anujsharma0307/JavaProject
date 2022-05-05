package sample.model.object.project;

import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import sample.DatabaseManager.SQLConnection;
import sample.model.object.taskmanagement.advanceTask;
import sample.model.object.taskmanagement.basicTask;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class projectColumn {
    private int columnID;
    private String columnName;
    private ArrayList<MenuItem> menuBasics;
    private ArrayList<MenuItem> menuAdvances;
    private ArrayList<basicTask> basicTasks;
    private ArrayList<advanceTask> advanceTasks;

    private  ArrayList<Button> basicEditButtons;
    private  ArrayList<Button> advanceEditButtons;
    private  ArrayList<Button> basicDeleteButtons;
    private  ArrayList<Button> advanceDeleteButtons;


    public projectColumn(int columnID,String columnName) {
        this.columnID = columnID;
        this.columnName = columnName;
        menuBasics = new ArrayList<>();
        menuAdvances = new ArrayList<>();

        basicTasks = new ArrayList<>();
        advanceTasks = new ArrayList<>();

        basicDeleteButtons = new ArrayList<>();
        basicEditButtons = new ArrayList<>();

        advanceEditButtons = new ArrayList<>();
        advanceDeleteButtons = new ArrayList<>();
    }

    public ArrayList<Button> getAdvanceDeleteButtons() {
        return advanceDeleteButtons;
    }

    public ArrayList<Button> getBasicDeleteButtons() {
        return basicDeleteButtons;
    }

    public ArrayList<Button> getAdvanceEditButtons() {
        return advanceEditButtons;
    }

    public ArrayList<Button> getBasicEditButtons() {
        return basicEditButtons;
    }

    public ArrayList<basicTask> getBasicTasks(){
        return basicTasks;
    }
    public ArrayList<advanceTask> getAdvanceTasks(){
        return advanceTasks;
    };

    public ArrayList<MenuItem> getMenuBasic(){
        return menuBasics;
    }
    public ArrayList<MenuItem> getMenuAdvance(){
        return menuAdvances;
    }
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnID(int columnID) {
        this.columnID = columnID;
    }

    public int getColumnID() {
        return columnID;
    }
    public void addBasicTasK(basicTask task,int ProjectID,int ColumnID){
        try{
            Statement statement = SQLConnection.getConnection().createStatement();
            String query = "select * from BasicTasks where TaskID='" + task.getTaskID() + "'";
            ResultSet result = statement.executeQuery(query);

            if (result.next()){
                return;
            }
            query = "insert into BasicTasks (TaskID,TaskName,TaskDescription,ProjectID,ColumnID) values(?,?,?,?,?)";
            PreparedStatement pstmt = SQLConnection.getConnection().prepareStatement(query);

            // set the corresponding param
            pstmt.setString(1, task.getTaskID() + "");
            pstmt.setString(2, task.getName());
            pstmt.setString(3, task.getDescription());
            pstmt.setString(4, ProjectID + "");
            pstmt.setString(5, ColumnID + "");
            pstmt.executeUpdate();
            pstmt.close();
            basicTasks.add(task);
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void addAdvanceTasK(advanceTask task,int ProjectID,int ColumnID){
        try{
            Statement statement = SQLConnection.getConnection().createStatement();
            String query = "select * from AdvanceTasks where TaskID='" + task.getTaskID() + "'";
            ResultSet result = statement.executeQuery(query);

            if (result.next()){
                return;
            }
            query = "insert into AdvanceTasks (TaskID,TaskName,TaskDescription,ProjectID,ColumnID,TaskDate) values(?,?,?,?,?,?)";
            PreparedStatement pstmt = SQLConnection.getConnection().prepareStatement(query);

            // set the corresponding param
            pstmt.setString(1, task.getTaskID() + "");
            pstmt.setString(2, task.getName());
            pstmt.setString(3, task.getDescription());
            pstmt.setString(4, ProjectID + "");
            pstmt.setString(5, ColumnID + "");

            LocalDate localDate = task.getDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
            String TaskDate = localDate.format(formatter);

            pstmt.setString(6, TaskDate);
            pstmt.executeUpdate();
            pstmt.close();
            advanceTasks.add(task);
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
