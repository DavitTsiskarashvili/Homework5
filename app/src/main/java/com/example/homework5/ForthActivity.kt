package com.example.homework5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.homework5.databinding.ActivityForthBinding
import com.example.homework5.databinding.ActivitySecondBinding
import com.example.homework5.databinding.ActivityThirdBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ForthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForthBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.btnLogIn2.setOnClickListener {
            loginInUser()
        }
        binding.btnLogIn2.setOnClickListener {
            Toast.makeText(this, "You are Logged in!", Toast.LENGTH_LONG).show()
        }

        binding.btnBack3.setOnClickListener {
            val intentBack = Intent(this, MainActivity::class.java)
            startActivity(intentBack)
        }
    }



    private fun loginInUser(){
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword2.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()){
            CoroutineScope(Dispatchers.IO).launch{
                try {auth.signInWithEmailAndPassword(email, password).await()

                }catch (e: Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@ForthActivity, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}