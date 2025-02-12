package ca.senecacollege.workshop.taskapp;

import ca.senecacollege.workshop.taskapp.models.Task;
import ca.senecacollege.workshop.taskapp.models.TaskList;
import ca.senecacollege.workshop.taskapp.views.TaskDetailsDialog;
import ca.senecacollege.workshop.taskapp.views.TaskPanel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class HelloApplication extends Application {
    private TaskList model;
    private TaskPanel view;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        model = new TaskList();
        view = new TaskPanel(model);

        view.getAddButton().setOnAction(e->{
            //call my taskDialogpanel window
            Task newTask = new Task();
            Dialog<Task> dialog = new TaskDetailsDialog(primaryStage, "New Task Details", newTask);
            var result = dialog.showAndWait();
            if(result.isPresent()){
                model.add(result.get());
                view.update();
            }
        });

        view.getRemoveButton().setOnAction(e->{
            int selectedIndex = view.getTaskList().getSelectionModel().getSelectedIndex();
            if(selectedIndex >= 0){
                model.remove(selectedIndex);
                view.update();
            }
        });

        view.getShowCompletedButton().setOnAction(e->{
            view.update();
        });

        view.getTaskList().setOnMousePressed(e->{
            if(e.getClickCount() == 2){
                Task selectedTask = view.getTaskList().getSelectionModel().getSelectedItem();
                if(selectedTask != null){
                    Dialog<Task> dialog = new TaskDetailsDialog(primaryStage,"Edit Task Details", selectedTask);
                    Optional<Task> result = dialog.showAndWait();
                    if(result.isPresent())
                        view.update();
                }
            }
        });

        primaryStage.setTitle("Task Manager");
        primaryStage.setScene(new Scene(view, 500,400));
        primaryStage.show();
    }
}