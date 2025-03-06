package com.example.dealify

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            // Set up the register link
            val registerLink = findViewById<TextView>(R.id.registerLink)
            if (registerLink != null) {
                Log.d(TAG, "Register link found")
                registerLink.setOnClickListener {
                    Log.d(TAG, "Register link clicked")
                    try {
                        val intent = Intent(this, RegisterActivity::class.java)
                        startActivity(intent)
                        finish()
                    } catch (e: Exception) {
                        Log.e(TAG, "Error starting RegisterActivity", e)
                    }
                }
            } else {
                Log.e(TAG, "Register link not found")
            }

            // Set up the login button
            val loginButton = findViewById<MaterialButton>(R.id.loginButton)
            if (loginButton != null) {
                Log.d(TAG, "Login button found")
                loginButton.setOnClickListener {
                    Log.d(TAG, "Login button clicked")
                    if (validateInputs()) {
                        try {
                            val intent = Intent(this, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        } catch (e: Exception) {
                            Log.e(TAG, "Error starting HomeActivity", e)
                        }
                    }
                }
            } else {
                Log.e(TAG, "Login button not found")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error in onCreate", e)
        }
    }

    private fun validateInputs(): Boolean {
        val emailInput = findViewById<TextInputEditText>(R.id.emailInput)
        val passwordInput = findViewById<TextInputEditText>(R.id.passwordInput)

        val email = emailInput?.text.toString().trim()
        val password = passwordInput?.text.toString().trim()

        // Validar email
        if (email.isEmpty()) {
            emailInput?.error = "Email is required"
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput?.error = "Please enter a valid email"
            return false
        }

        // Validar contraseña
        if (password.isEmpty()) {
            passwordInput?.error = "Password is required"
            return false
        }

        if (password.length < 6) {
            passwordInput?.error = "Password must be at least 6 characters"
            return false
        }

        // Aquí puedes agregar más validaciones específicas
        // Por ejemplo, verificar contra una base de datos o API
        if (email == "test@example.com" && password == "password123") {
            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
            return true
        } else {
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            return false
        }
    }
} 