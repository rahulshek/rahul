package com.example.collegecompanion.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "timetable_entries")
public class TimetableEntry {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String day;
    private String time;
    private String subject;
    private String classroom;
    private String lecturer;
    private String notes;

    public TimetableEntry(String day, String time, String subject, String classroom, String lecturer, String notes) {
        this.day = day;
        this.time = time;
        this.subject = subject;
        this.classroom = classroom;
        this.lecturer = lecturer;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
} 