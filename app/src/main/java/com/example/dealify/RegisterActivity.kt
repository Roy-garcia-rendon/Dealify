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

class RegisterActivity : AppCompatActivity() {
    private val TAG = "RegisterActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        try {
            // Set up the login link
            val loginLink = findViewById<TextView>(R.id.loginLink)
            if (loginLink != null) {
                Log.d(TAG, "Login link found")
                loginLink.setOnClickListener {
                    Log.d(TAG, "Login link clicked")
                    try {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } catch (e: Exception) {
                        Log.e(TAG, "Error starting MainActivity", e)
                    }
                }
            } else {
                Log.e(TAG, "Login link not found")
            }

            // Set up the register button
            val registerButton = findViewById<MaterialButton>(R.id.registerButton)
            if (registerButton != null) {
                Log.d(TAG, "Register button found")
                registerButton.setOnClickListener {
                    Log.d(TAG, "Register button clicked")
                    if (validateInputs()) {
                        Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
                        // Aquí podrías agregar la lógica para guardar el usuario
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            } else {
                Log.e(TAG, "Register button not found")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error in onCreate", e)
        }
    }

    private fun validateInputs(): Boolean {
        val nameInput = findViewById<TextInputEditText>(R.id.nameInput)
        val emailInput = findViewById<TextInputEditText>(R.id.emailInput)
        val passwordInput = findViewById<TextInputEditText>(R.id.passwordInput)
        val confirmPasswordInput = findViewById<TextInputEditText>(R.id.confirmPasswordInput)

        val name = nameInput?.text.toString().trim()
        val email = emailInput?.text.toString().trim()
        val password = passwordInput?.text.toString().trim()
        val confirmPassword = confirmPasswordInput?.text.toString().trim()

        // Validar nombre
        if (name.isEmpty()) {
            nameInput?.error = "Name is required"
            return false
        }

        if (name.length < 2) {
            nameInput?.error = "Name must be at least 2 characters"
            return false
        }

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

        // Validar confirmación de contraseña
        if (confirmPassword.isEmpty()) {
            confirmPasswordInput?.error = "Please confirm your password"
            return false
        }

        if (password != confirmPassword) {
            confirmPasswordInput?.error = "Passwords do not match"
            return false
        }

        return true
    }
} 