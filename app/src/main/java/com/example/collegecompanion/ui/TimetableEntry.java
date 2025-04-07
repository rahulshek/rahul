package com.example.collegecompanion.ui;

public class TimetableEntry {
    private String subjectName;
    private String time;
    private String faculty;

    public TimetableEntry(String subjectName, String time, String faculty) {
        this.subjectName = subjectName;
        this.time = time;
        this.faculty = faculty;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getTime() {
        return time;
    }

    public String getFaculty() {
        return faculty;
    }
} 