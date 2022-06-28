package com.example.homework5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.homework5.databinding.ActivityMainBinding
import com.example.homework5.databinding.ActivitySecondBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.btnNext.setOnClickListener{
            registerUser()
        }

        binding.btnNext.setOnClickListener {
            val intentNext = Intent(this, ThirdActivity::class.java)
            startActivity(intentNext) }

            binding.btnBack1.setOnClickListener {
                val intentBack = Intent(this, MainActivity::class.java)
                startActivity(intentBack) }

        }


    private fun registerUser(){
        val email = binding.etEmailRegister.text.toString()
        val password = binding.etPassword.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()){
            CoroutineScope(Dispatchers.IO).launch{
                try {auth.createUserWithEmailAndPassword(email, password).await()
                    withContext(Dispatchers.Main){
                        checkLoggedInState()
                    }
                }catch (e: Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@SecondActivity, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun checkLoggedInState() {
        if (auth.currentUser == null) {
            Toast.makeText(this,"you Haven't Registered", Toast.LENGTH_LONG).show()
        }
    }
}