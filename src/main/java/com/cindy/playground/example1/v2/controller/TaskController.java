package com.cindy.playground.example1.v2.controller;

import com.cindy.playground.example1.v2.model.Status;
import com.cindy.playground.example1.v2.model.Task;
import com.cindy.playground.example1.v2.model.TaskStorage;
import com.cindy.playground.example1.v2.view.View;

import java.util.UUID;

public class TaskController {
    private final View view;
    private final TaskStorage taskStorage;

    public TaskController() {
        view = new View();
        taskStorage = new TaskStorage();
    }

    public void control() {
        String choice;
        while (true) {
            choice = view.getString("Menu:\n1 - add,\n2 - update\n3 - delete\n4 - findall\n5 - change_progress\n");
            switch (choice) {
                case "add" -> handleAddAction();
                case "update" -> handleUpdateAction();
                case "delete" -> handleDelete();
                case "findall" -> findAll();
                case "change_progress" -> markAsDone();
                default -> view.print("Invalid choice: " + choice);
            }
        }
    }

    private void handleAddAction() {
        taskStorage.add(view.getString("name"), Status.TODO);
    }

    private void handleUpdateAction() {
        UUID uuid = UUID.fromString(view.getString("Provide UUI of the task to be updated: "));
        Task task = new Task(view.getString("name"), Status.TODO);
        task.setUuid(uuid);
        taskStorage.update(task);
    }

    private void handleDelete() {
        UUID uuid = UUID.fromString(view.getString("Enter the number of the task to remove: "));
        taskStorage.remove(String.valueOf(uuid));
    }

    private void findAll() {
        taskStorage.findAll().forEach(System.out::println);
    }

    private void markAsDone() {
        String uuidNumber = view.getString("Enter the uuid of the task to mark as done: ");
        taskStorage.markAsComplete(uuidNumber);
    }
}
//TODO: itd. kolejne opcje. :) oddaję mysze, prosze zaimplementuj pozostale akcje.
