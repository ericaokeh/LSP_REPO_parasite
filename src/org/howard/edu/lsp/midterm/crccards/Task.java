package org.howard.edu.lsp.midterm.crccards;

/**
 * Data Abstraction representing a Task.
 * Encapsulates task data and behavior.
 * 
 * @author Erica
 */
public class Task {

    private String taskId;
    private String description;
    private String status;

    /**
     * Constructs a Task.
     * 
     * @param taskId unique task ID
     * @param description task description
     */
    public Task(String taskId, String description) {
        if (taskId == null || taskId.isEmpty()) {
            throw new IllegalArgumentException("Every task needs a unique Identity (ID).");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description is required.");
        }

        this.taskId = taskId;
        this.description = description;
        this.status = "OPEN";
    }

    /**
     * Returns the task ID.
     *
     * @return task ID
     */
    public String getTaskId() {
        return this.taskId;
    }

    /**
     * Returns the task description.
     *
     * @return task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the current task status.
     *
     * @return task status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Sets the task status.
     * Valid values: OPEN, IN_PROGRESS, COMPLETE.
     * Otherwise sets to UNKNOWN.
     * 
     *@param status new status value
     */
    public void setStatus(String status) {
        if (status == null) {
            this.status = "UNKNOWN";
            return;
        }

        if (status.equals("OPEN") || status.equals("IN_PROGRESS") || status.equals("COMPLETE")) {
            this.status = status;
        } else {
            this.status = "UNKNOWN";
        }
    }

    /**
     * Returns a string representation of the task.
     *
     * @return formatted string with task ID, description, and status
     */
    @Override
    public String toString() {
        return taskId + " " + description + " [" + status + "]";
    }
}