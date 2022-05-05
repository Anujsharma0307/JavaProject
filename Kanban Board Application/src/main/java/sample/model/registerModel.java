package sample.model;

import sample.DatabaseManager.SQLConnection;

import java.sql.ResultSet;
import java.sql.Statement;

public class registerModel {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String avatar;

    public void registerModel(){
        username = password = firstname = lastname = "";
        avatar = "default.png";
    }

    public void registerModel(String username,String password,String firstname,String lastname,String avatar){
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.avatar = avatar;
    }

    public boolean signUp(){
        try{
            Statement statement = SQLConnection.getConnection().createStatement();
            String query = "select * from User where username='" + username + "'";
            ResultSet result = statement.executeQuery(query);

            if (result.next()){
                return false;
            }
            query = "insert into User (username,password,firstname,lastname,avatar)" +
                    "values('" + username + "','" + password + "','" + firstname + "','" +
                    lastname + "','" + avatar + "')";

            statement.executeUpdate(query);
            statement.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean validate(){

        if (username.isBlank() || firstname.isBlank() || lastname.isBlank() || password.isBlank())
            return false;
        return true;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
