module com.example.fpassignment2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens sample to javafx.fxml;
    exports sample;
    opens sample.controller.projectcontroller to javafx.fxml;
    opens sample.controller.taskcontroller to javafx.fxml;
    exports sample.controller.projectcontroller;
    exports sample.controller.usercontroller;
    opens sample.controller.usercontroller to javafx.fxml;

}