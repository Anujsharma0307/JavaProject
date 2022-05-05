package sample.model.object.taskmanagement;


import sample.DatabaseManager.SQLConnection;

import java.sql.PreparedStatement;

public class basicTask extends abstractTask {


    public basicTask(String name, String description, int taskid, boolean status) {

        super(name,description,taskid,status);
    }
    public void editTask(String name,String description){
        try {
            String query = "update BasicTasks set TaskName=?,TaskDescription=? where TaskID=?";
            PreparedStatement pstmt = SQLConnection.getConnection().prepareStatement(query);

            // set the corresponding param
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setInt(3,getTaskID());
            pstmt.executeUpdate();
            pstmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
