package sample.model.object.user;

import java.util.ArrayList;

public class user {
    private String firstname, lastname, username, password;
    private String imageFilePath;
    private boolean status;
    private int defaultProjectID;
    private ArrayList<Integer> userProjects;
    private boolean defaultImageStatus;


    public user(String firstname, String lastname, String username, String password, String imageFilePath,ArrayList<Integer> userProjects) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.imageFilePath =imageFilePath;
        this.userProjects= userProjects;
    }
    public void setDefaultImageStatus(boolean defaultImageStatus) {
        this.defaultImageStatus = true;
    }

    public boolean isDefaultImageStatus() {
        return defaultImageStatus;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    public ArrayList<Integer> getUserProjects() {
        return userProjects;
    }

    public void setUserProjects(ArrayList<Integer> userProjects) {
        this.userProjects = userProjects;
    }

    public int getDefaultProjectID() {
        return defaultProjectID;
    }

    public void setDefaultProjectID(int defaultProjectID) {
        this.defaultProjectID = defaultProjectID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

