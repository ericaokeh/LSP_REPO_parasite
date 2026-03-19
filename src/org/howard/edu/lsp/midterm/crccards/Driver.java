package org.howard.edu.lsp.midterm.crccards;

import java.util.List;
import java.util.Iterator;

public class Driver {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // These variables (t1, t2) refer to objects in memory.
        Task t1 = new Task("T1", "Finish Midterm");
        Task t2 = new Task("T2", "Grab Coffee");

        manager.addTask(t1);
        manager.addTask(t2);

        // Practice safe coding: Catch exceptions for "anything that can go wrong."
        try {
            manager.addTask(new Task("T1", "I'm a duplicate!"));
        } catch (IllegalArgumentException e) {
            System.out.println("Caught an identity conflict: " + e.getMessage());
        }

        // Display results using the Iterator pattern.
        List<Task> openTasks = manager.getTasksByStatus("OPEN");
        System.out.println("\n--- Current Tasks ---");
        
        Iterator<Task> it = openTasks.iterator();
        while (it.hasNext()) {
            // toString() is called implicitly here—classic OO behavior.
            System.out.println(it.next());
        }
        System.out.println("\n--- Find Task ---");
        System.out.println(manager.findTask("T1"));

        System.out.println("\n--- Invalid Status Test ---");
        try {
            t1.setStatus("DONE");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
