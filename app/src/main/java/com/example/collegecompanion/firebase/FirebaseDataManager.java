package com.example.collegecompanion.firebase;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.example.collegecompanion.data.AppDatabase;
import com.example.collegecompanion.data.NoteDao;
import com.example.collegecompanion.data.AssignmentDao;
import com.example.collegecompanion.data.AnnouncementDao;
import com.example.collegecompanion.data.Note;
import com.example.collegecompanion.data.Assignment;
import com.example.collegecompanion.data.Announcement;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FirebaseDataManager {
    private static FirebaseDataManager instance;
    private final NoteDao noteDao;
    private final AssignmentDao assignmentDao;
    private final AnnouncementDao announcementDao;
    private final ExecutorService executorService;

    private FirebaseDataManager(Context context) {
        AppDatabase database = AppDatabase.getInstance(context.getApplicationContext());
        noteDao = database.noteDao();
        assignmentDao = database.assignmentDao();
        announcementDao = database.announcementDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public static synchronized FirebaseDataManager getInstance(Context context) {
        if (instance == null) {
            instance = new FirebaseDataManager(context.getApplicationContext());
        }
        return instance;
    }

    // Notes operations
    public void addNote(Note note, DataCallback<Note> callback) {
        executorService.execute(() -> {
            try {
                noteDao.insert(note);
                callback.onSuccess(note);
            } catch (Exception e) {
                callback.onFailure(e.getMessage());
            }
        });
    }

    public LiveData<List<Note>> getNotes(String subject) {
        return noteDao.getNotesBySubject(subject);
    }

    public void deleteNote(Note note, DataCallback<Void> callback) {
        executorService.execute(() -> {
            try {
                noteDao.delete(note);
                callback.onSuccess(null);
            } catch (Exception e) {
                callback.onFailure(e.getMessage());
            }
        });
    }

    // Assignments operations
    public void addAssignment(Assignment assignment, DataCallback<Assignment> callback) {
        executorService.execute(() -> {
            try {
                assignmentDao.insert(assignment);
                callback.onSuccess(assignment);
            } catch (Exception e) {
                callback.onFailure(e.getMessage());
            }
        });
    }

    public LiveData<List<Assignment>> getAssignments() {
        return assignmentDao.getAllAssignments();
    }

    public void updateAssignment(Assignment assignment, DataCallback<Assignment> callback) {
        executorService.execute(() -> {
            try {
                assignmentDao.update(assignment);
                callback.onSuccess(assignment);
            } catch (Exception e) {
                callback.onFailure(e.getMessage());
            }
        });
    }

    public void deleteAssignment(Assignment assignment, DataCallback<Void> callback) {
        executorService.execute(() -> {
            try {
                assignmentDao.delete(assignment);
                callback.onSuccess(null);
            } catch (Exception e) {
                callback.onFailure(e.getMessage());
            }
        });
    }

    // Announcements operations
    public void addAnnouncement(Announcement announcement, DataCallback<Announcement> callback) {
        executorService.execute(() -> {
            try {
                announcementDao.insert(announcement);
                callback.onSuccess(announcement);
            } catch (Exception e) {
                callback.onFailure(e.getMessage());
            }
        });
    }

    public LiveData<List<Announcement>> getAnnouncements() {
        return announcementDao.getAllAnnouncements();
    }

    public void deleteAnnouncement(Announcement announcement, DataCallback<Void> callback) {
        executorService.execute(() -> {
            try {
                announcementDao.delete(announcement);
                callback.onSuccess(null);
            } catch (Exception e) {
                callback.onFailure(e.getMessage());
            }
        });
    }

    public interface DataCallback<T> {
        void onSuccess(T result);
        void onFailure(String error);
    }
} 