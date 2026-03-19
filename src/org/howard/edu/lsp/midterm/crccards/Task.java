package org.howard.edu.lsp.midterm.crccards;

/**
 *  Data Abstraction
 *  Encapsulation—hiding the raw data (private variables) 
 * so the rest of the system only interacts through our public methods.
 */
public class Task {

    // --- Variables ---
    // Kept private to ensure "Information Hiding." 
    // The outside world doesn't need to know how we store the strings.
    private String taskId;
    private String description;
    private String status;

    // --- Constructors ---
    /**
     * Set up the object. Since we don't provide a "setTaskId" method, 
     * the taskId stays "Immutable" after this point.
     */
    public Task(String taskId, String description) {
        // We validate here to protect the object's state.
        if (taskId == null || taskId.isEmpty()) {
            throw new IllegalArgumentException("Every task needs a unique Identity (ID).");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description is required.");
        }

        this.taskId = taskId;
        this.description = description;
        this.status = "OPEN"; // Default starting state
    }

    // --- Public Methods ---

    public String getTaskId() {
        return this.taskId;
    }

    public String getStatus() {
        return this.status;
    }

    /**
     * This is a "Procedural Abstraction." 
     * We hide the if-else logic inside this method so the user 
     * just calls "setStatus" and it "just works."
     */
    public void setStatus(String status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null.");
        }

        // We're keeping it simple and rejecting "clever" code.
        // Plain string comparison is easy for people to read.
        if (status.equalsIgnoreCase("OPEN") || 
            status.equalsIgnoreCase("IN_PROGRESS") || 
            status.equalsIgnoreCase("COMPLETE")) {
            
            this.status = status.toUpperCase();
        } else {
            // Error handling for invalid inputs as taught in the Java slides.
            throw new IllegalArgumentException("That's not a valid status");
        }
    }

    @Override
    public String toString() {
        return "Task ID: " + taskId + " | " + description + " [" + status + "]";
    }
}