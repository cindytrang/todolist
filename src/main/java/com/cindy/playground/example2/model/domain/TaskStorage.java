package com.cindy.playground.example2.model.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskStorage {
    private final List<Task> tasks = new ArrayList<>();

    public void add(String name, Status status) {
        tasks.add(new Task(name, status));
    }

    public void remove(String uuid) {
        UUID uuidObject = UUID.fromString(uuid);
        Task task = tasks.stream().filter(e -> e.getUuid().equals(uuidObject)).findFirst().orElseThrow();
        tasks.remove(task);
    }

    public void update(Task task) {
        tasks.remove(tasks.stream().filter(e -> e.getUuid().equals(task.getUuid())).findFirst().orElseThrow());
        tasks.add(task);
    }

    public void markAsComplete(String uuid) {
        Task taskToMarkAsComplete = tasks.stream().filter(e -> e.getUuid().equals(UUID.fromString(uuid))).findFirst().orElseThrow();
        Status currentStatus = taskToMarkAsComplete.getStatus();
        taskToMarkAsComplete.setStatus(Status.findNext(currentStatus));
        update(taskToMarkAsComplete);
    }

    public Task findById(String uuid) {
        return tasks.stream().filter(e -> e.getUuid().equals(UUID.fromString(uuid))).findFirst().orElseThrow();
    }

    public List<Task> findAll() {
        return tasks;
    }

}
