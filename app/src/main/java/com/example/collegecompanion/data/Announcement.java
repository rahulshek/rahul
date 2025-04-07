package com.example.collegecompanion.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "announcements")
public class Announcement {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String content;
    private String author;
    private long createdAt;

    public Announcement(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = System.currentTimeMillis();
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
} 