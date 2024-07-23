package com.cindy.playground.example2.model.repository.impl;

import com.cindy.playground.example2.model.domain.Task;
import com.cindy.playground.example2.model.repository.Repository;

import java.util.*;

// ConcreteStrategyB
public class InMemoryRepository implements Repository<Task, UUID> {
    private List<Task> tasks = new ArrayList<>();

    @Override
    public void save(Task obj) {
        tasks.add(obj);
    }

    @Override
    public List<Task> findAll() {
        return tasks;
    }

    @Override
    public Task findById(UUID uuid) {
        Task task = tasks.stream().filter(e -> e.getUuid().equals(uuid)).findFirst().orElseThrow();
        return task;
    }

    @Override
    public Task update(Task obj) {
        tasks.remove(tasks.stream().filter(e -> e.getUuid().equals(obj.getUuid())).findFirst().orElseThrow());
        tasks.add(obj);
        return obj;
    }

    @Override
    public Task delete(Task obj) {
        tasks.remove(tasks.stream().filter(e -> e.getUuid().equals(obj.getUuid())).findFirst().orElseThrow());
        return obj;
    }
}
