package ca.senecacollege.workshop.taskapp.views;

import ca.senecacollege.workshop.taskapp.models.Task;
import ca.senecacollege.workshop.taskapp.models.TaskList;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

/**
 * TaskPanel represents the View component in the MVC structure.
 * It displays a list of tasks and provides UI controls for adding,
 * removing and filtering tasks
 */
public class TaskPanel extends GridPane {
    private TaskList model;
    private ListView<Task> taskList;
    private Button addButton;
    private Button removeButton;
    private CheckBox showCompletedButton;

    /**
     * Constructor for TaskPanel
     * @param model The TaskList model containing task data
     */
    public TaskPanel(TaskList model){
        this.model = model;

        // Set up the layout
        setPadding(new Insets(10,10,10,10));
        setHgap(10);
        setVgap(10);

        // Initialize the ListView
        taskList = new ListView<>();
        taskList.setItems(FXCollections.observableArrayList(model.getTaskList()));
        add(taskList, 0, 0, 1, 3); // Span 1 column, 3 rows
        taskList.setPrefHeight(Integer.MAX_VALUE);
        taskList.setMinWidth(200);
        taskList.setPrefWidth(Integer.MAX_VALUE);

        // Initialize the Add button
        addButton = new Button("Add Task");
        add(addButton, 1, 0);
        setMargin(addButton, new Insets(0, 0, 10, 10));
        setValignment(addButton, VPos.TOP);
        setHalignment(addButton, HPos.CENTER);
        addButton.setMinHeight(25);
        addButton.setMinWidth(100);

        // Initialize the Remove button
        removeButton = new Button("Remove Task");
        add(removeButton, 1, 1);
        setMargin(removeButton, new Insets(0, 0, 10, 10));
        setValignment(removeButton, VPos.TOP);
        setHalignment(removeButton, HPos.CENTER);
        removeButton.setMinHeight(25);
        removeButton.setMinWidth(100);

        // Initialize the Show Completed CheckBox
        showCompletedButton = new CheckBox("Show Completed");
        add(showCompletedButton, 1, 2);
        setMargin(showCompletedButton, new Insets(0, 0, 10, 10));
        setValignment(showCompletedButton, VPos.TOP);
        setHalignment(showCompletedButton, HPos.CENTER);
        showCompletedButton.setMinHeight(25);
        showCompletedButton.setMinWidth(100);

        update(); // Update the UI with initial data
    }

    // return the ListView displaying tasks
    public ListView<Task> getTaskList() {
        return taskList;
    }

    // return the Add Task button
    public Button getAddButton() {
        return addButton;
    }

    // return the Remove Task button
    public Button getRemoveButton() {
        return removeButton;
    }

    // return the Show Completed checkbox
    public CheckBox getShowCompletedButton() {
        return showCompletedButton;
    }

    /**
     * Updates the task list display based on the selected filter.
     * - If "Show Completed" is checked, displays all tasks.
     * - Otherwise, only displays incomplete tasks.
     * - Also re-selects the previously selected item.
     */
    public void update(){
        int selectedIndex = taskList.getSelectionModel().getSelectedIndex();

        if(showCompletedButton.isSelected()){
            taskList.setItems(FXCollections.observableArrayList(model.getTaskList()));
        }
        else{
            taskList.setItems(FXCollections.observableArrayList(model.getInCompletedTasks()));
        }

        //Reselect the previously selectd item
        taskList.getSelectionModel().select(selectedIndex);

        // Disable the remove button if no task is selected
        removeButton.setDisable(taskList.getSelectionModel().getSelectedIndex() < 0);
    }
}
