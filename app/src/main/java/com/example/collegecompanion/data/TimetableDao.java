package com.example.collegecompanion.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TimetableDao {
    @Insert
    void insert(TimetableEntry entry);

    @Update
    void update(TimetableEntry entry);

    @Delete
    void delete(TimetableEntry entry);

    @Query("SELECT * FROM timetable_entries ORDER BY day, startTime")
    LiveData<List<TimetableEntry>> getAllEntries();

    @Query("SELECT * FROM timetable_entries WHERE day = :day ORDER BY startTime")
    LiveData<List<TimetableEntry>> getEntriesByDay(String day);
} 