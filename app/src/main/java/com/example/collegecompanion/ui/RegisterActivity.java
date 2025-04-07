package com.example.collegecompanion.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.collegecompanion.R;
import com.example.collegecompanion.auth.AuthManager;
import com.example.collegecompanion.data.User;
import com.example.collegecompanion.databinding.ActivityRegisterBinding;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private AuthManager authManager;

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@(.+)$"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        authManager = AuthManager.getInstance(this);
        setupClickListeners();
    }

    private void setupClickListeners() {
        binding.buttonRegister.setOnClickListener(v -> attemptRegistration());
        binding.textViewLogin.setOnClickListener(v -> finish());
    }

    private void attemptRegistration() {
        String fullName = binding.editTextFullName.getText().toString().trim();
        String email = binding.editTextEmail.getText().toString().trim();
        String password = binding.editTextPassword.getText().toString().trim();
        String confirmPassword = binding.editTextConfirmPassword.getText().toString().trim();

        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidEmail(email)) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
            return;
        }

        authManager.signUp(fullName, email, password);
        authManager.getCurrentUser().observe(this, user -> {
            if (user != null) {
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        authManager.getErrorMessage().observe(this, error -> {
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
} 