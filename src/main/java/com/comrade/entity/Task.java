package com.comrade.entity;

public class Task {

    private String name;
    private String taskName;

    public Task() {
    }

    public Task(String name, String taskName) {
        this.name = name;
        this.taskName = taskName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
