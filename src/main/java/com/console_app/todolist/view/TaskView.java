package com.console_app.todolist.view;

import com.console_app.todolist.model.Task;
import java.util.List;


public class TaskView {
    public void displayTasks(List<Task> tasks) {
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
}