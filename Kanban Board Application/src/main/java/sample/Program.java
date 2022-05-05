package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.DatabaseManager.SQLConnection;

import java.io.IOException;

public class Program extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        if (!SQLConnection.connect())
            return;
        FXMLLoader fxmlLoader = new FXMLLoader(Program.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Login to SmartBoard!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}