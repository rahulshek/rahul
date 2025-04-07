package com.example.collegecompanion.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    LiveData<User> getUserByEmailAndPassword(String email, String password);

    @Query("SELECT * FROM users WHERE email = :email")
    LiveData<User> getUserByEmail(String email);
} 