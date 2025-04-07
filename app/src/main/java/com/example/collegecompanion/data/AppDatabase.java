package com.example.collegecompanion.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.collegecompanion.data.UserDao;
import com.example.collegecompanion.data.NoteDao;
import com.example.collegecompanion.data.AssignmentDao;
import com.example.collegecompanion.data.AnnouncementDao;
import com.example.collegecompanion.data.User;
import com.example.collegecompanion.data.Note;
import com.example.collegecompanion.data.Assignment;
import com.example.collegecompanion.data.Announcement;
import com.example.collegecompanion.data.TimetableEntry;
import com.example.collegecompanion.data.TimetableDao;

@Database(entities = {
    User.class,
    Note.class,
    Assignment.class,
    Announcement.class,
    TimetableEntry.class
}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public abstract UserDao userDao();
    public abstract NoteDao noteDao();
    public abstract AssignmentDao assignmentDao();
    public abstract AnnouncementDao announcementDao();
    public abstract TimetableDao timetableDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "college_companion_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
} 