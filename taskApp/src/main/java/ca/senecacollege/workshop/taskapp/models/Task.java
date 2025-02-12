package ca.senecacollege.workshop.taskapp.models;

public class Task {
    private String name;
    private String description;
    private String priority;
    private boolean completed;

    public Task(){this("","","Low");}

    public Task(String name, String description, String priority) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.completed = false;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getPriority() { return priority; }

    public void setPriority(String priority) { this.priority = priority; }

    public boolean isCompleted() { return completed; }

    public void setCompleted(boolean completed) { this.completed = completed; }

    @Override
    public String toString() { return name + "(" + priority + ") - " + (completed ? "Completed" : "InComplete"); }
}
