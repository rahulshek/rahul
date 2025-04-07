package com.example.collegecompanion.data;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.collegecompanion.data.NoteDao;
import com.example.collegecompanion.data.AssignmentDao;
import com.example.collegecompanion.data.AnnouncementDao;
import com.example.collegecompanion.data.TimetableDao;
import com.example.collegecompanion.data.Note;
import com.example.collegecompanion.data.Assignment;
import com.example.collegecompanion.data.Announcement;
import com.example.collegecompanion.data.TimetableEntry;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private final NoteDao noteDao;
    private final AssignmentDao assignmentDao;
    private final AnnouncementDao announcementDao;
    private final TimetableDao timetableDao;
    private final ExecutorService executorService;
    private final LiveData<List<Note>> allNotes;
    private final LiveData<List<Assignment>> allAssignments;
    private final LiveData<List<Announcement>> allAnnouncements;
    private final LiveData<List<TimetableEntry>> allTimetableEntries;

    public Repository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        noteDao = database.noteDao();
        assignmentDao = database.assignmentDao();
        announcementDao = database.announcementDao();
        timetableDao = database.timetableDao();
        executorService = Executors.newSingleThreadExecutor();
        allNotes = noteDao.getAllNotes();
        allAssignments = assignmentDao.getAllAssignments();
        allAnnouncements = announcementDao.getAllAnnouncements();
        allTimetableEntries = timetableDao.getAllEntries();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public LiveData<List<Assignment>> getAllAssignments() {
        return allAssignments;
    }

    public LiveData<List<Announcement>> getAllAnnouncements() {
        return allAnnouncements;
    }

    public LiveData<List<TimetableEntry>> getAllTimetableEntries() {
        return allTimetableEntries;
    }

    public void insert(Note note) {
        executorService.execute(() -> noteDao.insert(note));
    }

    public void insert(Assignment assignment) {
        executorService.execute(() -> assignmentDao.insert(assignment));
    }

    public void insert(Announcement announcement) {
        executorService.execute(() -> announcementDao.insert(announcement));
    }

    public void insert(TimetableEntry entry) {
        executorService.execute(() -> timetableDao.insert(entry));
    }

    // Notes operations
    public void insertNote(Note note) {
        executorService.execute(() -> noteDao.insert(note));
    }

    public LiveData<List<Note>> getNotesBySubject(String subject) {
        return noteDao.getNotesBySubject(subject);
    }

    public void deleteNote(Note note) {
        executorService.execute(() -> noteDao.delete(note));
    }

    // Assignments operations
    public void updateAssignment(Assignment assignment) {
        executorService.execute(() -> assignmentDao.update(assignment));
    }

    public void deleteAssignment(Assignment assignment) {
        executorService.execute(() -> assignmentDao.delete(assignment));
    }

    // Announcements operations
    public void deleteAnnouncement(Announcement announcement) {
        executorService.execute(() -> announcementDao.delete(announcement));
    }
} 