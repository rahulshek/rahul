package com.example.collegecompanion;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import com.example.collegecompanion.databinding.ActivityLoginBinding;
import com.example.collegecompanion.auth.AuthManager;
import com.example.collegecompanion.data.User;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private ActivityLoginBinding binding;
    private AuthManager authManager;
    private boolean isLoginMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            binding = ActivityLoginBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            Log.d(TAG, "LoginActivity created");
            authManager = AuthManager.getInstance(this);

            // Set up observers
            authManager.getCurrentUser().observe(this, new Observer<User>() {
                @Override
                public void onChanged(User user) {
                    if (user != null) {
                        Log.d(TAG, "User logged in: " + user.getEmail());
                        Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                        startMainActivity();
                    }
                }
            });

            authManager.getErrorMessage().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String error) {
                    if (error != null) {
                        Log.e(TAG, "Error: " + error);
                        Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
                    }
                }
            });

            // Set up click listeners
            binding.buttonToggleMode.setOnClickListener(v -> toggleMode());
            binding.buttonSubmit.setOnClickListener(v -> handleSubmit());
            binding.buttonGuest.setOnClickListener(v -> continueAsGuest());

            // Initialize UI state
            toggleMode();
        } catch (Exception e) {
            Log.e(TAG, "Error in onCreate: " + e.getMessage());
            Toast.makeText(this, "Error initializing login screen", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void toggleMode() {
        try {
            isLoginMode = !isLoginMode;
            Log.d(TAG, "Toggling mode to: " + (isLoginMode ? "Login" : "Sign Up"));
            if (isLoginMode) {
                binding.textInputName.setVisibility(View.GONE);
                binding.buttonSubmit.setText("Login");
                binding.buttonToggleMode.setText("Create Account");
            } else {
                binding.textInputName.setVisibility(View.VISIBLE);
                binding.buttonSubmit.setText("Sign Up");
                binding.buttonToggleMode.setText("Already have an account?");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error in toggleMode: " + e.getMessage());
        }
    }

    private void handleSubmit() {
        try {
            String email = binding.editEmail.getText().toString().trim();
            String password = binding.editPassword.getText().toString().trim();
            String name = binding.editName.getText().toString().trim();

            Log.d(TAG, "Handling submit in " + (isLoginMode ? "Login" : "Sign Up") + " mode");

            if (TextUtils.isEmpty(email)) {
                binding.editEmail.setError("Email is required");
                return;
            }

            if (TextUtils.isEmpty(password)) {
                binding.editPassword.setError("Password is required");
                return;
            }

            if (!isLoginMode && TextUtils.isEmpty(name)) {
                binding.editName.setError("Name is required");
                return;
            }

            if (isLoginMode) {
                Log.d(TAG, "Attempting login for: " + email);
                authManager.signIn(email, password);
            } else {
                Log.d(TAG, "Attempting sign up for: " + email);
                if (authManager.signUp(email, password, name)) {
                    Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show();
                    toggleMode();
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Error in handleSubmit: " + e.getMessage());
            Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();
        }
    }

    private void continueAsGuest() {
        Log.d(TAG, "Continuing as guest");
        startMainActivity();
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
} 