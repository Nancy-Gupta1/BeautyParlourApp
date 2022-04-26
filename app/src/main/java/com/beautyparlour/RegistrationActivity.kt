package com.beautyparlour

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import com.beautyparlour.models.RegisterModels
import com.beautyparlour.utils.BaseActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_registration.*


class RegistrationActivity : BaseActivity() {

    private lateinit var mAuth: FirebaseAuth
    var database: DatabaseReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        mAuth = FirebaseAuth.getInstance()


        val userName = findViewById<EditText>(R.id.edtUserName)
        val email = findViewById<EditText>(R.id.edtEmail)
        val mobile = findViewById<EditText>(R.id.edtMobile)
        val password = findViewById<EditText>(R.id.edtPassword)
        val conformpassword = findViewById<EditText>(R.id.edtConformPassword)
        val buttonsignup = findViewById<Button>(R.id.btnSignup)
        val buttonsignIn = findViewById<TextView>(R.id.btnSignIn)

        buttonsignIn.setOnClickListener {
          startActivity(Intent(this,LoginActivity::class.java))
            finish()

        }

        buttonsignup.setOnClickListener {

            if (userName.text.toString().isEmpty()) {
                edtUserName.error = "Please enter full name"
                edtUserName.requestFocus()


            } else if (email.text.toString().isEmpty()) {
                edtEmail.error = "Palese enter valid email"
                edtEmail.requestFocus()


            } else if (mobile.text.toString().length < 8) {
                edtEmail.error = "Enter mobile number"
                edtEmail.requestFocus()

            } else if (password.text.toString().isEmpty()) {
                edtPassword.error = "Please enter valid password"
                edtPassword.requestFocus()

            } else if (!conformpassword.text.toString().equals(password.text.toString())) {
                Toast.makeText(this, "Password not match", Toast.LENGTH_SHORT).show()
                edtConformPassword.requestFocus()
            } else {
                database = FirebaseDatabase.getInstance().getReference("Registartion")
                val userKey = database!!.push().key
                val userdata = RegisterModels(
                    userName.text.toString().trim(),
                    email.text.toString().trim(),
                    mobile.text.toString().trim(),
                    password.text.toString().trim()
                )
                database!!.child(userKey!!).setValue(userdata).addOnCompleteListener {
                    Toast.makeText(this, "Data Insert", Toast.LENGTH_SHORT).show()
                    edtUserName.setText("")
                    edtEmail.setText("")
                    edtMobile.setText("")
                    edtPassword.setText("")
                    edtConformPassword.setText("")
                }
                    mAuth.createUserWithEmailAndPassword(
                        email.text.toString().trim(),
                        password.text.toString().trim()
                    )
                        .addOnCompleteListener(
                            this@RegistrationActivity,
                            object : OnCompleteListener<AuthResult> {
                                override fun onComplete(@NonNull task: Task<AuthResult>) {
                                    if (task.isSuccessful) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("TAG", "createUserWithEmail:success")
                                        startActivity(
                                            Intent(
                                                this@RegistrationActivity,
                                                LoginActivity::class.java
                                            )
                                        )
                                        finish()
                                        val user = mAuth.currentUser
                                        updateUI(user)
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(
                                            "TAG",
                                            "createUserWithEmail:failure",
                                            task.exception
                                        )
                                        Toast.makeText(
                                            this@RegistrationActivity, "Authentication failed.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        updateUI(null)
                                    }

                                    // ...
                                }
                            })

                }
            }

        }


        public override fun onStart() {
            super.onStart()
            // Check if user is signed in (non-null) and update UI accordingly.
            val currentUser = mAuth.currentUser
            updateUI(currentUser)
        }


        private fun updateUI(nothing: FirebaseUser?) {

        }

}
