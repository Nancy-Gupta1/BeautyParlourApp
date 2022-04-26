package com.beautyparlour

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.custom_toolbar.*
import java.util.regex.Pattern

//https://www.youtube.com/watch?v=CKqIpDqjj44&t=272s

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        val toolbar: Toolbar = findViewById(R.id.toolbar1)
        setSupportActionBar(toolbar)
        toolbarName.text = getString(R.string.action_forgot)
        mAuth = FirebaseAuth.getInstance()
        initView()
    }

    private fun initView() {
        menu_btn.setOnClickListener {
            onBackPressed()

        }
        val email = findViewById<EditText>(R.id.edtEmailPassword)
        val Continue = findViewById<Button>(R.id.btnContinue)

        Continue.setOnClickListener {
            if (email.text.toString().isEmpty()) {
                edtEmailPassword.error = "Please Enter valid Email"
                edtEmailPassword.requestFocus()
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
                return@setOnClickListener

            }

            mAuth.sendPasswordResetEmail(email.text.toString()).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Email Send", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}