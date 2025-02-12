package ca.senecacollege.workshop.taskapp.views;

import ca.senecacollege.workshop.taskapp.models.Task;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TaskDetailsDialog extends Dialog<Task> {

    public TaskDetailsDialog(Stage owner, String title, Task task){
        setTitle(title);

        // Create a custom button with the label "Save" and associate it with OK_DONE action
        ButtonType okBtn = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);

        // Add the custom "Save" button and a default "Cancel" button to the dialog's button pane
        getDialogPane().getButtonTypes().addAll(okBtn, ButtonType.CANCEL);

        // Create the dialog content
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        // Initializes input fields with existing task data
        TextField nameField = new TextField(task.getName());
        TextArea descriptionField = new TextArea(task.getDescription());
        descriptionField.setPrefRowCount(3); // Set preferred number of rows
        ComboBox<String> priorityComboBox = new ComboBox<>();
        priorityComboBox.getItems().addAll("Low", "Medium", "High");
        priorityComboBox.setValue(task.getPriority()); // Set initial value
        CheckBox completedCheckBox = new CheckBox("Completed");
        completedCheckBox.setSelected(task.isCompleted()); // Set initial state

        // Arranges labels and input fields in a grid layout
        grid.add(new Label("Task Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Description:"), 0, 1);
        grid.add(descriptionField, 1, 1);
        grid.add(new Label("Priority:"), 0, 2);
        grid.add(priorityComboBox, 1, 2);
        grid.add(completedCheckBox, 1, 3); // Add the Completed CheckBox

        getDialogPane().setContent(grid);

        // Convert the result into Task Object
        // setResultConverter method: Converts the user’s input into a result object (Task) when the dialog is closed
        setResultConverter(btnType->{
            if(btnType == okBtn){
                task.setName(nameField.getText());
                task.setDescription(descriptionField.getText());
                task.setPriority(priorityComboBox.getValue());
                task.setCompleted(completedCheckBox.isSelected());
                return task;
            }
            return null;
        });
    }

}
