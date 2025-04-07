package com.example.collegecompanion.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "assignments")
public class Assignment {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private long dueDate;
    private String subject;
    private boolean isCompleted;

    public Assignment(String title, String description, long dueDate, String subject) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.subject = subject;
        this.isCompleted = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public long getDueDate() {
        return dueDate;
    }

    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
} 