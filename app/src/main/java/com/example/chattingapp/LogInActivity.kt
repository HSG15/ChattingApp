package com.example.chattingapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chattingapp.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {

    lateinit var binding: ActivityLogInBinding
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()


        binding.btnSignUp.setOnClickListener {
            val intent=Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            if(TextUtils.isEmpty(binding.edtEmail.text.toString()) || TextUtils.isEmpty(binding.edtPassword.text.toString())){
                Toast.makeText(this,"Please fill the data",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
                }else{
                    binding.btnLogin.visibility = View.INVISIBLE
                    binding.proLogIn.visibility = View.VISIBLE
            }

            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            login(email,password)
        }
    }
    private fun login(email:String,password:String){

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent = Intent(this,MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,"User doesn't exist",Toast.LENGTH_SHORT).show()
                }
            }
    }
}