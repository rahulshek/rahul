package com.example.collegecompanion.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AnnouncementDao {
    @Insert
    void insert(Announcement announcement);

    @Update
    void update(Announcement announcement);

    @Delete
    void delete(Announcement announcement);

    @Query("SELECT * FROM announcements ORDER BY createdAt DESC")
    LiveData<List<Announcement>> getAllAnnouncements();

    @Query("SELECT * FROM announcements WHERE id = :id")
    LiveData<Announcement> getAnnouncementById(int id);
} 