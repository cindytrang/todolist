package com.cindy.playground.example2.model.repository.impl;

import com.cindy.playground.example2.model.domain.Status;
import com.cindy.playground.example2.model.domain.TaskStorage;
import com.cindy.playground.example2.model.repository.Repository;
import com.cindy.playground.example2.model.domain.Task;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

// ConcreteStrategyA
public class FileRepository implements Repository<Task, UUID> {
    public FileRepository() {
        try {
            if(Files.size((Path.of("tasks.bin"))) == 0) {
                reloadFile(new ArrayList<>());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Task obj) { //used while addding the item
        List<Task> tasks = findAll();
        tasks.add(obj);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("tasks.bin"))) {
            outputStream.writeObject(tasks);
            System.out.println("Tasks saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Task> findAll() { //used for loading from the repository
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("tasks.bin"))) {
            List<Task> tasks = (List<Task>) inputStream.readObject();
            System.out.println("Loaded: " + tasks);
            return tasks;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Task findById(UUID uuid) {
        List<Task> tasks = findAll();
        Task task = tasks.stream().filter(e -> e.getUuid().equals(uuid)).findFirst().orElseThrow();
        return task;
    }

    @Override
    public Task update(Task obj) {
        List<Task> tasks = findAll();
        tasks.remove(tasks.stream().filter(e -> e.getUuid().equals(obj.getUuid())).findFirst().orElseThrow());
        tasks.add(obj);
        reloadFile(tasks);
        return obj;
    }

    @Override
    public Task delete(Task obj) {
        List<Task> tasks = findAll();
        tasks.remove(tasks.stream().filter(e -> e.getUuid().equals(obj.getUuid())).findFirst().orElseThrow());
        reloadFile(tasks);
        return obj;
    }

    private void reloadFile(List<Task> tasks) { //save tasks
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("tasks.bin"))) {
            outputStream.writeObject(tasks);
            System.out.println("Tasks saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
