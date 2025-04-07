package com.example.collegecompanion.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("SELECT * FROM notes ORDER BY createdAt DESC")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM notes WHERE id = :id")
    LiveData<Note> getNoteById(int id);

    @Query("SELECT * FROM notes WHERE subject = :subject")
    LiveData<List<Note>> getNotesBySubject(String subject);
} 