package com.example.collegecompanion.ui;

public class AssignmentEntry {
    private String subject;
    private String description;
    private String dueDate;
    private boolean isCompleted;

    public AssignmentEntry(String subject, String description, String dueDate) {
        this.subject = subject;
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = false;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
} 