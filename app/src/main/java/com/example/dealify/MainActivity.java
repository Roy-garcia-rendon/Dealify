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

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            // Set up the register link
            TextView registerLink = findViewById(R.id.registerLink);
            if (registerLink != null) {
                Log.d(TAG, "Register link found");
                registerLink.setOnClickListener(v -> {
                    Log.d(TAG, "Register link clicked");
                    try {
                        Intent intent = new Intent(this, RegisterActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (Exception e) {
                        Log.e(TAG, "Error starting RegisterActivity", e);
                    }
                });
            } else {
                Log.e(TAG, "Register link not found");
            }

            // Set up the login button
            MaterialButton loginButton = findViewById(R.id.loginButton);
            if (loginButton != null) {
                Log.d(TAG, "Login button found");
                loginButton.setOnClickListener(v -> {
                    Log.d(TAG, "Login button clicked");
                    if (validateInputs()) {
                        try {
                            Intent intent = new Intent(this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } catch (Exception e) {
                            Log.e(TAG, "Error starting HomeActivity", e);
                        }
                    }
                });
            } else {
                Log.e(TAG, "Login button not found");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error in onCreate", e);
        }
    }

    private boolean validateInputs() {
        TextInputEditText emailInput = findViewById(R.id.emailInput);
        TextInputEditText passwordInput = findViewById(R.id.passwordInput);

        String email = emailInput != null ? emailInput.getText().toString().trim() : "";
        String password = passwordInput != null ? passwordInput.getText().toString().trim() : "";

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

        // Validar credenciales específicas
        if (email.equals("test@example.com") && password.equals("password123")) {
            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}