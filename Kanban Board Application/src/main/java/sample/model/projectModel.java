package sample.model;

import javafx.scene.paint.Color;
import sample.DatabaseManager.SQLConnection;
import sample.model.object.project.project;
import sample.model.object.project.projectColumn;
import sample.model.object.taskmanagement.advanceTask;
import sample.model.object.taskmanagement.basicTask;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class projectModel {

    ArrayList<project> projects;
    ArrayList<projectColumn> projectColumns;
    ArrayList<basicTask> basicTasks;
    ArrayList<advanceTask> advanceTasks;

    public projectModel(){
        projects = new ArrayList<>();
        projectColumns = new ArrayList<>();
        basicTasks = new ArrayList<>();
        advanceTasks = new ArrayList<>();
    }
    public void addProject(project pro){
        try{
            Statement statement = SQLConnection.getConnection().createStatement();
            String query = "select * from Projects where ProjectID='" + pro.getProjectID() + "'";
            ResultSet result = statement.executeQuery(query);

            if (result.next()){
                return;
            }
            query = "insert into Projects (ProjectID,ProjectName) values(?,?)";
            PreparedStatement pstmt = SQLConnection.getConnection().prepareStatement(query);

            // set the corresponding param
            pstmt.setString(1, pro.getProjectID() + "");
            pstmt.setString(2, pro.getProjectName());

            pstmt.executeUpdate();
            pstmt.close();
            projects.add(pro);
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void editProject(int ProjectID,String projectRename){
        try{
            project pro = projects.get(ProjectID);

            String query = "update Projects set ProjectName = ? where ProjectID = ?";
            PreparedStatement pstmt = SQLConnection.getConnection().prepareStatement(query);

            // set the corresponding param
            pstmt.setString(1, projectRename);
            pstmt.setInt(2,pro.getProjectID());

            pstmt.executeUpdate();
            pstmt.close();
            pro.setProjectName(projectRename);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void addColumn(int projectID,projectColumn column){

        try{
            Statement statement = SQLConnection.getConnection().createStatement();
            String query = "select * from Columns where ColumnID='" + column.getColumnID() + "'";
            ResultSet result = statement.executeQuery(query);

            if (result.next()){
                return;
            }
            query = "insert into Columns (ColumnID,ColumnName,ProjectID) values(?,?,?)";
            PreparedStatement pstmt = SQLConnection.getConnection().prepareStatement(query);

            // set the corresponding param
            pstmt.setString(1, column.getColumnID() + "");
            pstmt.setString(2, column.getColumnName());
            pstmt.setString(3, projectID + "");

            pstmt.executeUpdate();
            pstmt.close();

            projects.get(projectID).addColumnn(column);
            projectColumns.add(column);
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void deleteColumn(){

    }
    public ArrayList<project> getProjects(){
        return projects;
    }
    public int getProjectsNumber(){
        return projects.size();
    }
    public int getProjectColumnNumber(){
        return projectColumns.size();
    }
    public int getBasicTaskNumber(){
        return basicTasks.size();
    }
    public ArrayList<basicTask> getBasicTasks(){
        return basicTasks;
    }
    public ArrayList<advanceTask> getAdvanceTasks(){
        return advanceTasks;
    }
    public void LoadProjects(){
        try{
            Statement statement = SQLConnection.getConnection().createStatement();
            String query = "select * from Projects";
            ResultSet result = statement.executeQuery(query);
            projects = new ArrayList<>();
            while (result.next()){
                int projectID = result.getInt("ProjectID");
                String projectName = result.getString("ProjectName");
                projects.add(new project(projectID,projectName));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void LoadColumns(){
        try{
            Statement statement = SQLConnection.getConnection().createStatement();
            String query = "select * from Columns";
            ResultSet result = statement.executeQuery(query);
            projectColumns = new ArrayList<>();
            while (result.next()){
                int ColumnID = result.getInt("ColumnID");
                String ColumnName = result.getString("ColumnName");
                projectColumns.add(new projectColumn(ColumnID,ColumnName));
            }

            for(int i = 0;i < projects.size();i++){
                query = "select * from Columns where ProjectID = '" + projects.get(i).getProjectID() + "'";
                result = statement.executeQuery(query);
                while (result.next()){
                    int ColumnID = result.getInt("ColumnID");
                    String ColumnName = result.getString("ColumnName");
                    projects.get(i).addColumnn(new projectColumn(ColumnID,ColumnName));
                }
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void LoadBasicTasks(){
        try{
            Statement statement = SQLConnection.getConnection().createStatement();
            String query = "select * from BasicTasks";
            ResultSet result = statement.executeQuery(query);
            basicTasks = new ArrayList<>();
            while (result.next()){
                int TaskID = result.getInt("TaskID");
                String TaskName = result.getString("TaskName");
                String TaskDescription = result.getString("TaskDescription");
                int ProjectID = result.getInt("ProjectID");
                int ColumnID = result.getInt("ColumnID");
                basicTask task = new basicTask(TaskName,TaskDescription,TaskID,false);
                basicTasks.add(task);
                projects.get(ProjectID).getProjectColumns().get(ColumnID).getBasicTasks().add(task);
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void LoadAdvanceTasks(){
        try{
            Statement statement = SQLConnection.getConnection().createStatement();
            String query = "select * from AdvanceTasks";
            ResultSet result = statement.executeQuery(query);
            advanceTasks = new ArrayList<>();
            while (result.next()){
                int TaskID = result.getInt("TaskID");
                String TaskName = result.getString("TaskName");
                String TaskDescription = result.getString("TaskDescription");
                int ProjectID = result.getInt("ProjectID");
                int ColumnID = result.getInt("ColumnID");
                String TaskDate = result.getString("TaskDate");
                SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy");
                Date date = formatter.parse(TaskDate);
                LocalDate localDate = date.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                advanceTask task = new advanceTask(TaskName,TaskDescription,TaskID,false,localDate,new Color(0,0,1,0));
                advanceTasks.add(task);
                projects.get(ProjectID).getProjectColumns().get(ColumnID).getAdvanceTasks().add(task);
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void deleteProject(int ProjectID){
        try{
            project pro = projects.get(ProjectID);

            String query = "delete from Projects where ProjectID = ?";
            PreparedStatement pstmt = SQLConnection.getConnection().prepareStatement(query);
            // set the corresponding param
            pstmt.setInt(1,pro.getProjectID());
            pstmt.executeUpdate();
            pstmt.close();

            query = "delete from Columns where ProjectID = ?";
            pstmt = SQLConnection.getConnection().prepareStatement(query);
            // set the corresponding param
            pstmt.setInt(1,pro.getProjectID());
            pstmt.executeUpdate();
            pstmt.close();

            query = "delete from BasicTasks where ProjectID = ?";
            pstmt = SQLConnection.getConnection().prepareStatement(query);
            // set the corresponding param
            pstmt.setInt(1,pro.getProjectID());
            pstmt.executeUpdate();
            pstmt.close();

            query = "delete from AdvanceTasks where ProjectID = ?";
            pstmt = SQLConnection.getConnection().prepareStatement(query);
            // set the corresponding param
            pstmt.setInt(1,pro.getProjectID());
            pstmt.executeUpdate();
            pstmt.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void deleteBasicTask(int ProjectID,int ColumnID,int TaskID){
        try{
            project pro = projects.get(ProjectID);

            String query = "delete from BasicTasks where ProjectID = ? and ColumnID = ? and TaskID = ?";
            PreparedStatement pstmt = SQLConnection.getConnection().prepareStatement(query);
            // set the corresponding param
            pstmt.setInt(1,pro.getProjectID());
            pstmt.setInt(2,pro.getProjectColumns().get(ColumnID).getColumnID());
            pstmt.setInt(3,pro.getProjectColumns().get(ColumnID).getBasicTasks().get(TaskID).getTaskID());
            pstmt.executeUpdate();
            pstmt.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void deleteAdvanceTask(int ProjectID,int ColumnID,int TaskID){
        try{
            project pro = projects.get(ProjectID);

            String query = "delete from AdvanceTasks where ProjectID = ? and ColumnID = ? and TaskID = ?";
            PreparedStatement pstmt = SQLConnection.getConnection().prepareStatement(query);
            // set the corresponding param
            pstmt.setInt(1,pro.getProjectID());
            pstmt.setInt(2,pro.getProjectColumns().get(ColumnID).getColumnID());
            pstmt.setInt(3,pro.getProjectColumns().get(ColumnID).getAdvanceTasks().get(TaskID).getTaskID());
            pstmt.executeUpdate();
            pstmt.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
