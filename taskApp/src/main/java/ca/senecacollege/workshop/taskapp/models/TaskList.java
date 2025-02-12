package ca.senecacollege.workshop.taskapp.models;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    // List to store Task objects
    private List<Task> tasks;

    // Constructor initializes an empty task list
    public TaskList() { tasks = new ArrayList<>(); }

    // Returns the total number of tasks in the list
    public int getSize(){
        return tasks.size();
    }

    // Returns the entire list of tasks
    public List<Task> getTaskList(){
        return tasks;
    }

    // Retrieves a task at a specific index
    // Returns null if the index is out of bounds
    public Task getTask(int index){
        if(index >= 0 && index < tasks.size()){
            return tasks.get(index);
        }
        else return null;
    }

    // Adds a new task to the list
    public void add(Task task){
        tasks.add(task);
    }

    // Index is valid ? Removes a task at the specified index
    public void remove(int index){
        if(index >= 0 && index < tasks.size()){
            tasks.remove(index);
        }
    }

    // Returns a list of tasks filtered by priority (case-insensitive)
    public List<Task> getTaskByPriority(String priority){
        List<Task> filteredTasks = new ArrayList<>();
        for(Task task : tasks){
            if(task.getPriority().equalsIgnoreCase(priority)){
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    // Mark a particular task completed
    public void markTaskCompleted(int index){
        if(index >= 0 && index < tasks.size()){
            Task task = tasks.get(index);
            task.setCompleted(true);
        }
    }

    // Get all completed tasks list
    public List<Task> getCompletedTasks(){
        List<Task> completedTasks = new ArrayList<>();
        for(Task task : tasks){
            if(task.isCompleted()){
                completedTasks.add(task);
            }
        }
        return completedTasks;
    }

    // Get all incomplete tasks
    public List<Task> getInCompletedTasks(){
        List<Task> inCompletedTasks = new ArrayList<>();
        for(Task task : tasks){
            if(!task.isCompleted()){
                inCompletedTasks.add(task);
            }
        }
        return inCompletedTasks;
    }
}
