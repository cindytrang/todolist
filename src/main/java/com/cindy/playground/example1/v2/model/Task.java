package com.cindy.playground.example1.v2.model;

import java.util.UUID;


public class Task {
    private UUID uuid = UUID.randomUUID();
    private String name;
    private Status status;

    public Task(String name, Status status) {
        this.name = name;
        this.status = status;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "uuid=" + uuid.toString() +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
