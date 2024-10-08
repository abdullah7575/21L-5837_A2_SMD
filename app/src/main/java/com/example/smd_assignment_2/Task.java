package com.example.smd_assignment_2;

// Model Class
public class Task {
    private String name;
    private String description;

    public Task(){

    }
    public Task(String name,String description){
        this.name = name;
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
