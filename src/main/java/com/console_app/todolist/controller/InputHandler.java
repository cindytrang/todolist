package com.console_app.todolist.controller;

import com.console_app.todolist.view.TaskView;

import org.springframework.stereotype.Component;

import java.util.*;
import lombok.*;

/**
 * This class manages the user input in the to-do list application.
 * It handles canceling the operation and correct task selection.
 */
@Component
public class InputHandler {
    private TaskView taskView;
    private TaskController taskController;


    public InputHandler(TaskView taskView) {
        this.taskView = taskView;
    }

    public int getMenuChoice() {
        while (true) {
            try {
                int choice = taskView.getIntInput();
                if (choice >= 1 && choice <= 6) {
                    return choice;
                }
                System.out.println("Invalid option. Please enter a number between 1 and 6.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public String getInput(String prompt) {
        System.out.println(prompt);
        return taskView.getStringInput();
    }

    public int getTaskIndex(String prompt) {
        while (true) {
            System.out.println(prompt + " (or 'c' to cancel)");
            String input = taskView.getStringInput().trim().toLowerCase();

            if (input.equals("c")) {
                System.out.println("Operation cancelled.");
                return -1;
            }

            try {
                int index = Integer.parseInt(input) - 1;
                if (index >= 0 && index < taskView.getTaskCount()) {
                    return index;
                }
                System.out.println("Invalid task number. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number or 'c' to cancel.");
            }
        }
    }

    public String getUpdatedField(String prompt, String currentValue) {
        System.out.println(prompt + " (Current: " + currentValue + ")");
        String input = taskView.getStringInput();
        return input.isEmpty() ? currentValue : input;
    }
}

