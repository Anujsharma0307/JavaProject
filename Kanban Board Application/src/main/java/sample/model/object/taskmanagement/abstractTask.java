package sample.model.object.taskmanagement;

import javafx.scene.paint.Color;

import java.util.Date;

abstract class abstractTask {
    protected String name;
    protected String description;
    protected int taskID;
    protected boolean status;

    protected abstractTask(String name, String description,int taskID,boolean status) {
        this.name = name;
        this.description = description;
        this.taskID=taskID;
        this.status=status;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
