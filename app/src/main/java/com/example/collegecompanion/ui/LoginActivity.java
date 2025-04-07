package com.example.collegecompanion.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.collegecompanion.R;
import com.example.collegecompanion.auth.AuthManager;
import com.example.collegecompanion.data.User;
import com.example.collegecompanion.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private AuthManager authManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        authManager = AuthManager.getInstance(this);

        if (authManager.isUserLoggedIn()) {
            startMainActivity();
            return;
        }

        setupClickListeners();
    }

    private void setupClickListeners() {
        binding.buttonLogin.setOnClickListener(v -> attemptLogin());
        binding.textViewRegister.setOnClickListener(v -> startRegisterActivity());
    }

    private void attemptLogin() {
        String email = binding.editTextEmail.getText().toString().trim();
        String password = binding.editTextPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        authManager.signIn(email, password);
        authManager.getCurrentUser().observe(this, user -> {
            if (user != null) {
                startMainActivity();
            }
        });
        authManager.getErrorMessage().observe(this, error -> {
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void startRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
} 