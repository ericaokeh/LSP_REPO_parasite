package org.howard.edu.lsp.midterm.crccards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator; // The modern, preferred way over old Enumerations.

/**
 * This class manages our collection of Task objects.
 * It follows the "Modularity" principle—all logic for managing tasks 
 * is self-contained right here.
 */
public class TaskManager {

    // --- Variables ---
    // We use a Map because "Identity" matters. 
    // Two tasks with the same data are different objects, 
    // but the ID key ensures we don't have logic conflicts.
    private Map<String, Task> tasks;

    // --- Constructors ---
    public TaskManager() {
        // Initializing the collection as a HashMap.
        this.tasks = new HashMap<>();
    }

    // --- Public Methods ---

    /**
     * Adds a new task to the system.
     * Crucial OO Concept: We ensure each object has a distinct Identity.
     */
    public void addTask(Task task) {
        if (task == null || task.getTaskId() == null) {
            throw new IllegalArgumentException("Can't add a ghost task.");
        }

        // Check if this Identity already exists in our system.
        if (tasks.containsKey(task.getTaskId())) {
            throw new IllegalArgumentException("Duplicate ID error: Task " + task.getTaskId() + " already exists.");
        }

        tasks.put(task.getTaskId(), task);
    }

    /**
     * Filters tasks by status.
     * We're using the Iterator pattern (while loop + hasNext) 
     * because the slides highlighted this as the standard Java approach.
     */
    public List<Task> getTasksByStatus(String status) {
        if (status == null) {
            throw new IllegalArgumentException("Status filter cannot be null.");
        }

        List<Task> result = new ArrayList<>();
        
        // Grab an Iterator. This is simpler and less "clever" than Streams,
        // which fits the teacher's style guidelines.
        Iterator<Task> taskIterator = tasks.values().iterator();
        
        while (taskIterator.hasNext()) {
            Task current = taskIterator.next();
            // Using polymorphism
            if (current.getStatus().equalsIgnoreCase(status)) {
                result.add(current);
            }
        }
        return result;
    }

    public Task findTask(String taskId) {
        // Quick look-up via the Map.
        if (taskId == null) {
            throw new IllegalArgumentException("Search ID cannot be null.");
        }
        return tasks.get(taskId);
    }
}