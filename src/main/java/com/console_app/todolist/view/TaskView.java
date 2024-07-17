package com.console_app.todolist.view;

import com.console_app.todolist.model.Task;
import com.console_app.todolist.controller.TaskController;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Component
public class TaskView {
    @Autowired
    private TaskController taskController;
    private final Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        System.out.println("\n--- To-Do List Menu ---");
        System.out.println("1. Add Task");
        System.out.println("2. View Tasks");
        System.out.println("3. Update Task");
        System.out.println("4. Mark Task as Complete");
        System.out.println("5. Remove Task");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    public void displayTasks() {
        List<Task> tasks = taskController.getAllTasks();

        if (tasks.isEmpty()) {
            System.out.println("No tasks to display.");
        } else {
            System.out.println("Task List:");
            for (Task task : tasks) {
                System.out.println(task.getTitle() + " [" + (task.isCompleted() ? "Completed" : "Not Completed") + "]");
            }
        }
    }

    public void displayTask(Task task) {
        System.out.println(task.getTitle() + ": " + task.getDescription() + " [" + (task.isCompleted() ? "Completed" : "Not Completed") + "]");
    }

    public int getIntInput() {
        return scanner.nextInt();
    }

    public String getStringInput() {
        return scanner.nextLine();
    }
}