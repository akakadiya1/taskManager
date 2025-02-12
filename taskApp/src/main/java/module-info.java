module ca.senecacollege.workshop.taskapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens ca.senecacollege.workshop.taskapp to javafx.fxml;
    exports ca.senecacollege.workshop.taskapp;
}