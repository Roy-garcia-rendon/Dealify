package com.example.dealify;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        try {
            // Set up the login link
            TextView loginLink = findViewById(R.id.loginLink);
            if (loginLink != null) {
                Log.d(TAG, "Login link found");
                loginLink.setOnClickListener(v -> {
                    Log.d(TAG, "Login link clicked");
                    try {
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (Exception e) {
                        Log.e(TAG, "Error starting MainActivity", e);
                    }
                });
            } else {
                Log.e(TAG, "Login link not found");
            }

            // Set up the register button
            MaterialButton registerButton = findViewById(R.id.registerButton);
            if (registerButton != null) {
                Log.d(TAG, "Register button found");
                registerButton.setOnClickListener(v -> {
                    Log.d(TAG, "Register button clicked");
                    if (validateInputs()) {
                        Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            } else {
                Log.e(TAG, "Register button not found");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error in onCreate", e);
        }
    }

    private boolean validateInputs() {
        TextInputEditText nameInput = findViewById(R.id.nameInput);
        TextInputEditText emailInput = findViewById(R.id.emailInput);
        TextInputEditText passwordInput = findViewById(R.id.passwordInput);
        TextInputEditText confirmPasswordInput = findViewById(R.id.confirmPasswordInput);

        String name = nameInput != null ? nameInput.getText().toString().trim() : "";
        String email = emailInput != null ? emailInput.getText().toString().trim() : "";
        String password = passwordInput != null ? passwordInput.getText().toString().trim() : "";
        String confirmPassword = confirmPasswordInput != null ? confirmPasswordInput.getText().toString().trim() : "";

        // Validar nombre
        if (name.isEmpty()) {
            if (nameInput != null) {
                nameInput.setError("Name is required");
            }
            return false;
        }

        if (name.length() < 2) {
            if (nameInput != null) {
                nameInput.setError("Name must be at least 2 characters");
            }
            return false;
        }

        // Validar email
        if (email.isEmpty()) {
            if (emailInput != null) {
                emailInput.setError("Email is required");
            }
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            if (emailInput != null) {
                emailInput.setError("Please enter a valid email");
            }
            return false;
        }

        // Validar contraseña
        if (password.isEmpty()) {
            if (passwordInput != null) {
                passwordInput.setError("Password is required");
            }
            return false;
        }

        if (password.length() < 6) {
            if (passwordInput != null) {
                passwordInput.setError("Password must be at least 6 characters");
            }
            return false;
        }

        // Validar confirmación de contraseña
        if (confirmPassword.isEmpty()) {
            if (confirmPasswordInput != null) {
                confirmPasswordInput.setError("Please confirm your password");
            }
            return false;
        }

        if (!password.equals(confirmPassword)) {
            if (confirmPasswordInput != null) {
                confirmPasswordInput.setError("Passwords do not match");
            }
            return false;
        }

        return true;
    }
} 