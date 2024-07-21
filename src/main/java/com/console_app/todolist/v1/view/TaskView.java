package com.console_app.todolist.v1.view;

import com.console_app.todolist.v1.model.Task;
import com.console_app.todolist.v1.controller.TaskController;
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
        System.out.println("4. Change Status");
        System.out.println("5. Remove Task");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    public void displayTasks() {
        List<Task> tasks = taskController.getAllTasks();

        if (tasks.isEmpty()) {
            System.out.println("No tasks to display.");
        } else {
            System.out.println("------------------------Task List------------------------");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                String status = task.isCompleted() ? "[Completed]" : "[Incomplete]";
                System.out.printf(" %d. | %s | %s | %s |\n",
                        (i + 1),
                        task.getTitle(),
                        status,
                        task.getDescription());
            }
            System.out.println("--------------------------------------------------------");
        }
    }

    public int getIntInput() {
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    public String getStringInput() {
        return scanner.nextLine();
    }

    public int getTaskCount() {
        return taskController.getTasks().size();
    }
}