package com.example.ifinances

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ifinances.databinding.ActivityMainLoginBinding
import com.example.ifinances.databinding.ActivityRegisterScreenBinding

class MainLogin : AppCompatActivity() {
    private lateinit var binding: ActivityMainLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.Login.setOnClickListener {
            intent = Intent(this, LoginScreen::class.java)
            startActivity(intent)
            finish()
        }

        binding.register.setOnClickListener {
            intent = Intent(this, RegisterScreen::class.java)
            startActivity(intent)
            finish()

        }
    }


}