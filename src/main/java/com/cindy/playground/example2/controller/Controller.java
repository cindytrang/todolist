package com.cindy.playground.example2.controller;

import com.cindy.playground.example2.model.domain.Status;
import com.cindy.playground.example2.model.domain.Task;
import com.cindy.playground.example2.model.repository.RepositoryContext;
import com.cindy.playground.example2.model.repository.impl.FileRepository;
import com.cindy.playground.example2.model.repository.impl.InMemoryRepository;
import com.cindy.playground.example2.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Controller {
    private final View view;
    private RepositoryContext<Task, UUID> taskRepositoryContext;

    public Controller() {
        taskRepositoryContext = new RepositoryContext<>(); //initially as file repository can be changed to the memory
        view = new View();
    }

    public void control() {
        String strategy  = view.getString("Do you want to use file or in-memory system management? ");
        switch (strategy) {
            case "file", "in-memory" -> setupRepository(strategy);
            default -> view.print("Invalid choice: " + strategy);
        }

        String choice;
        while (true) {
            choice = view.getString("Menu:\n1 - add,\n2 - update\n3 - delete\n4 - findall\n5 - change_progress\n5 - save\n5 - findId\n");
            switch (choice) {
                case "1", "add" -> handleAddAction();
                case "2", "update" -> handleUpdateAction();
                case "3", "delete" -> handleDelete();
                case "4", "findall" -> findAll();
                case "5", "change_progress" -> markAsDone();
                case "6", "findId" -> printTask();
                //in meomry repository
                default -> view.print("Invalid choice: " + choice);
            }
        }
    }

    public void setupRepository(String type) {
        switch (type) {
            case "file" -> taskRepositoryContext.setRepository(new FileRepository());
            case "in-memory" -> taskRepositoryContext.setRepository(new InMemoryRepository());
        }
    }

    private void handleAddAction() {
        Task task = new Task(view.getString("name"), Status.TODO);
        taskRepositoryContext.save(task);
    }

    private void handleUpdateAction() {
        UUID uuid = UUID.fromString(view.getString("111Provide UUI of the task to be updated: "));
        Task task = new Task(view.getString("name"), Status.DONE);
        task.setUuid(uuid);
        taskRepositoryContext.update(task);
    }

    private void handleDelete() {
        UUID uuid = UUID.fromString(view.getString("Enter the number of the task to remove: "));
        List<Task> tasks = taskRepositoryContext.findAll();
        Task task = tasks.stream().filter(e -> e.getUuid().equals(uuid)).findFirst().orElseThrow();
        taskRepositoryContext.delete(task);
    }

    private void findAll() {
        List<Task> tasks = taskRepositoryContext.findAll();
        tasks.forEach(System.out::println);
    }

    private void markAsDone() {
        UUID uuidNumber = UUID.fromString(view.getString("Enter the uuid of the task to mark as done: "));
        List<Task> tasks = taskRepositoryContext.findAll();
        Task task = tasks.stream().filter(e -> e.getUuid().equals(uuidNumber)).findFirst().orElseThrow();
        Status currentStatus = task.getStatus();
        task.setStatus(Status.findNext(currentStatus));
        taskRepositoryContext.update(task);
    }

    private void printTask() {
        UUID uuid = UUID.fromString(view.getString("Provide UUI of the task to be found: "));
        Task task = taskRepositoryContext.findById(uuid);
        System.out.println("Task found " + task);

    }

}
