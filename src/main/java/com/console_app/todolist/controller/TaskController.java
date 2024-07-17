package com.console_app.todolist.controller;

import com.console_app.todolist.model.Task;
import com.console_app.todolist.view.TaskView;
import org.springframework.stereotype.Component;

import java.util.*;
import lombok.*;

/**
 * This class manages the operations on tasks in the to-do list.
 * It handles adding, removing, updating, and marking tasks as complete.
 */
@Component
public class TaskController {
    @Getter
    private final List<Task> tasks = new ArrayList<>();

    public void addTask(String title, String description) {
        tasks.add(new Task(title, description, false));
    }

    public void removeTask(int index) {
        if(isValidIndex(index)) {
            tasks.remove(index);
        }
    }

    public void updateTask(int index, String title, String description) {
        if(isValidIndex(index)) {
            Task task = tasks.get(index);
            task.setTitle(title);
            task.setDescription(description);
        }
    }

    public void markTaskInComplete(int index) {
        if(isValidIndex(index)) {
            tasks.get(index).setCompleted(false);
        }
    }

    public Task getTask(int index) {
        if (isValidIndex(index)) {
            return tasks.get(index);
        }
        return null;
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }


    public boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }
}
