package sample.model.object.taskmanagement;
import javafx.scene.paint.Color;
import sample.DatabaseManager.SQLConnection;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class advanceTask extends abstractTask {
    protected LocalDate date;
    protected Color color;

    public advanceTask(String name, String description, int taskid, boolean status, LocalDate date, Color color) {
        super(name,description,taskid,status);
        this.date=date;
        this.color=color;
    }
    public void editTask(String name,String description,LocalDate localDate){
        try {
            String query = "update AdvanceTasks set TaskName=?,TaskDescription=?,TaskDate=? where TaskID=?";
            PreparedStatement pstmt = SQLConnection.getConnection().prepareStatement(query);


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
            String TaskDate = localDate.format(formatter);

            // set the corresponding param
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setString(3,TaskDate);
            pstmt.setInt(4,getTaskID());
            pstmt.executeUpdate();
            pstmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
