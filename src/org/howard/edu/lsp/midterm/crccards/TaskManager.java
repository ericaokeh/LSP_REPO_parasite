package org.howard.edu.lsp.midterm.crccards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

/**
 * This class manages our collection of Task objects.
 * It follows the "Modularity" principle—all logic for managing tasks
 * is self-contained right here.
 * 
 * @author Erica
 */
public class TaskManager {

    private Map<String, Task> tasks;

    /**
     * Constructs an empty TaskManager.
     */
    public TaskManager() {
        this.tasks = new HashMap<>();
    }

    /**
     * Adds a new task to the system.
     * Ensures each object has a distinct identity.
     * 
     * @param task the task to add
     * @throws IllegalArgumentException if task is null, has null ID,
     * or if a duplicate ID exists.
     */
    public void addTask(Task task) {
        if (task == null || task.getTaskId() == null) {
            throw new IllegalArgumentException("Can't add a ghost task.");
        }

        if (tasks.containsKey(task.getTaskId())) {
            throw new IllegalArgumentException(
                "Duplicate ID error: Task " + task.getTaskId() + " already exists."
            );
        }

        tasks.put(task.getTaskId(), task);
    }

    /**
     * Filters tasks by status.
     * Uses the Iterator pattern explicitly.
     * 
     * @param status the status to filter
     * @return list of tasks matching status
     */
    public List<Task> getTasksByStatus(String status) {
        if (status == null) {
            throw new IllegalArgumentException("Status filter cannot be null.");
        }

        List<Task> result = new ArrayList<>();
        Iterator<Task> it = tasks.values().iterator();

        while (it.hasNext()) {
            Task current = it.next();
            if (current.getStatus().equals(status)) {
                result.add(current);
            }
        }

        return result;
    }

    /**
     * Finds a task by ID.
     * 
     * @param taskId the ID to search
     * @return the Task if found, otherwise null
     */
    public Task findTask(String taskId) {
        if (taskId == null) {
            return null;
        }
        return tasks.get(taskId);
    }
}