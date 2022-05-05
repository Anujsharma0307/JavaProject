package sample.DatabaseManager;

import java.sql.*;

public class SQLConnection {

    private static Connection connection;

    public SQLConnection(){

    }
    public static boolean connect(){
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:Project.db");
            if (connection != null)
                return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
    public static boolean close(){
        try{
            if (connection != null)
                connection.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public static Connection getConnection(){
        return connection;
    }
}
