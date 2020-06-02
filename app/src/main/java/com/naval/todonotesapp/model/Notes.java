package com.naval.todonotesapp.model;

public class Notes {
    String title;
    String description;

    public Notes()
    {

    }

    public Notes(String t,String desc)
    {
        this.title = t;
        this.description = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
