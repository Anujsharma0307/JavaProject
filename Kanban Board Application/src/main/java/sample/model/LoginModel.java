package sample.model;

import sample.DatabaseManager.SQLConnection;

import java.security.spec.ECField;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginModel {
    private static String username;
    private static String password;
    private static String firstname;
    private static String lastname;
    private static String avatarImageUrl;
    public static boolean loginStatus = false;

    public static String getFirstname(){
        return firstname;
    }
    public static String getLastname(){
        return lastname;
    }
    public static String getUsername(){
        return username;
    }
    public static String getAvatarImageUrl(){
        return avatarImageUrl;
    }
    public static void setFirstname(String fname){
        firstname = fname;
    }
    public static void setUsername(String uname){
        username = uname;
    }
    public static void setLastname(String lname){
        lastname = lname;
    }
    public static void setAvatarImageUrl(String url){
        avatarImageUrl = url;
    }

    public LoginModel(String username,String password){
        this.username = username;
        this.password = password;
    }
    public boolean loginUser(){
        try{
            String query = "select * from User where username='" + username + "' and password='" + password +"'";
            Statement stmt = null;
            stmt = SQLConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(query);

            int userid = result.getInt("id");
            String name = result.getString("username");
            String pass = result.getString("password");
            firstname = result.getString("firstname");
            lastname = result.getString("lastname");
            avatarImageUrl = result.getString("avatar");
            stmt.close();

            if (name.compareTo(username) == 0 && pass.compareTo(password) == 0)
                return true;
            else
                return false;


        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static void saveUser(){
        try{
            String query = "update User set username=?,firstname=?, lastname=?,avatar=?";
            PreparedStatement pstmt = SQLConnection.getConnection().prepareStatement(query);

            // set the corresponding param
            pstmt.setString(1, username);
            pstmt.setString(2, firstname);
            pstmt.setString(3, lastname);
            pstmt.setString(4, avatarImageUrl);

            pstmt.executeUpdate();
            pstmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void clearInfo(){
        username = "";
        firstname = "";
        lastname = "";
        password = "";
        avatarImageUrl = "";
        loginStatus = false;
    }
}
