package com.example.homework5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.homework5.databinding.ActivitySecondBinding
import com.example.homework5.databinding.ActivityThirdBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack2.setOnClickListener {
            val intentBack = Intent(this, SecondActivity::class.java)
            startActivity(intentBack) }

        binding.btnSignUp.setOnClickListener {
            Toast.makeText(this, "You are signed up!", Toast.LENGTH_LONG).show() }

    }
}