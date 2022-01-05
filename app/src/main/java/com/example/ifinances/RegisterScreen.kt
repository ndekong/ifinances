package com.example.ifinances

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.ifinances.databinding.ActivityRegisterScreenBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterScreen : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterScreenBinding
    private lateinit var actionBar: ActionBar
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""
    private var confirmpassword = ""

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.SignUp.setOnClickListener {
            validateData()
        }
    }
    private fun validateData() {
        email = binding.email.text.toString().trim()
        password = binding.Password.text.toString().trim()
        confirmpassword = binding.ConfirmPassword.text.toString()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.email.error = "Invalid email format"
        }
        else if (TextUtils.isEmpty(password)){
            binding.Password.error = "Please enter password"
        }
        else if (password.length <6) {
            binding.Password.error = "Password must be at least 6 characters"
        }
        else if (TextUtils.isEmpty(confirmpassword)){
            binding.Password.error = "Please confirm password"
        }
        else if (password != confirmpassword)
            binding.ConfirmPassword.error = "Your password do not match please confirm"

        else {
            firebaseSignUp()
        }
    }
    private fun firebaseSignUp() {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val firebaseUser =firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "Sign up Successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Dashboard::class.java))
                finish()
            }
            .addOnFailureListener {e->
                Toast.makeText(this, "Sign up fail due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}