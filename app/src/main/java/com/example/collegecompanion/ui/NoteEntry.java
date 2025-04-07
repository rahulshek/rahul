package com.example.collegecompanion.ui;

public class NoteEntry {
    private String subject;
    private String topic;
    private String pdfUrl;

    public NoteEntry(String subject, String topic, String pdfUrl) {
        this.subject = subject;
        this.topic = topic;
        this.pdfUrl = pdfUrl;
    }

    public String getSubject() {
        return subject;
    }

    public String getTopic() {
        return topic;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }
} 