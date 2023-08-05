package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private val binding: ActivitySplashBinding by lazy{
        ActivitySplashBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnGetStarted.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}