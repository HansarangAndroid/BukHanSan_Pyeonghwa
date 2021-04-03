package com.example.lecturesopt28th

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.lecturesopt28th.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        login()
    }

    private fun login() {
        binding.buttonLogin.setOnClickListener {
            if(checkInputText()) {
                Toast.makeText(this, "Please input email or password", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Success input checking", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun checkInputText(): Boolean {
        return (binding.edittextEmail.text.isNullOrEmpty()
                && binding.edittextPassword.text.isNullOrEmpty())
    }
}