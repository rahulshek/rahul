package com.example.collegecompanion.auth;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.collegecompanion.data.AppDatabase;
import com.example.collegecompanion.data.User;
import com.example.collegecompanion.data.UserDao;

public class AuthManager {
    private static final String PREFS_NAME = "CollegeCompanionPrefs";
    private static final String KEY_USER_EMAIL = "user_email";
    private static final String KEY_USER_NAME = "user_name";
    
    private final SharedPreferences sharedPreferences;
    private final UserDao userDao;
    private static AuthManager instance;
    private final MutableLiveData<User> currentUser;
    private final MutableLiveData<String> errorMessage;
    
    private AuthManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        userDao = AppDatabase.getInstance(context).userDao();
        currentUser = new MutableLiveData<>();
        errorMessage = new MutableLiveData<>();
        
        // Check if user is logged in from SharedPreferences
        String savedEmail = sharedPreferences.getString(KEY_USER_EMAIL, null);
        if (savedEmail != null) {
            loadUserFromDatabase(savedEmail);
        }
    }
    
    public static synchronized AuthManager getInstance(Context context) {
        if (instance == null) {
            instance = new AuthManager(context.getApplicationContext());
        }
        return instance;
    }
    
    private void loadUserFromDatabase(String email) {
        userDao.getUserByEmail(email).observeForever(user -> {
            if (user != null) {
                currentUser.postValue(user);
            } else {
                clearUserSession();
            }
        });
    }
    
    public void signIn(String email, String password) {
        userDao.getUserByEmailAndPassword(email, password).observeForever(user -> {
            if (user != null) {
                saveUserSession(user);
                currentUser.postValue(user);
                errorMessage.postValue(null);
            } else {
                errorMessage.postValue("Invalid email or password");
            }
        });
    }
    
    public void signUp(String name, String email, String password) {
        userDao.getUserByEmail(email).observeForever(existingUser -> {
            if (existingUser != null) {
                errorMessage.postValue("Email already exists");
                return;
            }

            User newUser = new User(name, email, password);
            userDao.insert(newUser);
            saveUserSession(newUser);
            currentUser.postValue(newUser);
            errorMessage.postValue(null);
        });
    }
    
    public void signOut() {
        clearUserSession();
        currentUser.postValue(null);
    }
    
    public boolean isUserLoggedIn() {
        return sharedPreferences.contains(KEY_USER_EMAIL);
    }
    
    public LiveData<User> getCurrentUser() {
        return currentUser;
    }
    
    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
    
    private void saveUserSession(User user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_EMAIL, user.getEmail());
        editor.putString(KEY_USER_NAME, user.getFullName());
        editor.apply();
    }
    
    private void clearUserSession() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
} 