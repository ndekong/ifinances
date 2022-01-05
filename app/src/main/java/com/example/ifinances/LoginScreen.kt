package com.example.ifinances


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.ifinances.databinding.ActivityLoginScreenBinding
import com.google.firebase.auth.FirebaseAuth

class LoginScreen : AppCompatActivity() {
    private lateinit var binding: ActivityLoginScreenBinding
    private lateinit var actionBar: ActionBar
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences
    var isRemembered =  false
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        isRemembered = sharedPreferences.getBoolean("CHECKBOX", false)





        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.SignUp.setOnClickListener {
            startActivity(Intent(this, RegisterScreen::class.java))
        }

        binding.Login.setOnClickListener {
            validateData()



        }
}

    private fun validateData() {
        email = binding.email.text.toString().trim()
        password = binding.Password.text.toString().trim()


        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.email.error = "Invalid email"
        }
        else if (TextUtils.isEmpty(password)){
            binding.Password.error = "Incorrect password"
        }
        else{
            firebaseLogin()
        }

    }


    private fun firebaseLogin() {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener{
                val firebaseUser =firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Dashboard::class.java))
                finish()

            }
            .addOnFailureListener {e->
                Toast.makeText(this, "Login fail due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null){
            startActivity(Intent(this, Dashboard::class.java))
                finish()
    }

}
}