package com.beautyparlour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import com.beautyparlour.utils.BaseActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registration.*
import com.beautyparlour.utils.PreferenceHelper.set

class LoginActivity : BaseActivity() {
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()

        val email = findViewById<EditText>(R.id.edtLEmail)
        val password = findViewById<EditText>(R.id.edtLPassword)
        val signIn = findViewById<Button>(R.id.btnLSignIn)
        val Signup = findViewById<TextView>(R.id.txtSignup)
        val skip = findViewById<TextView>(R.id.txtLoginSkip)
        val forgotPassword = findViewById<TextView>(R.id.txtForgotPassword)

        Signup.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))

        }
        forgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))


        }
        skip.setOnClickListener {
            startActivity(Intent(this, NavigationActivity::class.java))
            finish()

        }

        signIn.setOnClickListener {

            if (email.text.toString().isEmpty()) {
                edtLEmail.error = "Palese enter valid email"
                edtLEmail.requestFocus()


            } else if (password.text.toString().isEmpty()) {
                Toast.makeText(this, "Password not match", Toast.LENGTH_SHORT).show()
                edtLPassword.requestFocus()

            } else {
                mAuth.signInWithEmailAndPassword(
                    email.text.toString().trim(),
                    password.text.toString().trim()
                )
                    .addOnCompleteListener(
                        this@LoginActivity,
                        object : OnCompleteListener<AuthResult> {
                            override fun onComplete(@NonNull task: Task<AuthResult>) {
                                if (task.isSuccessful) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("TAG", "signInWithEmail:success")
                                    prifre["IS_LOGIN"]=true
                                    startActivity(Intent(this@LoginActivity, NavigationActivity::class.java)
                                    )
                                    finish()

                                    val user = mAuth.currentUser
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                                    Toast.makeText(
                                        this@LoginActivity, "Authentication failed.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        })
            }
        }
    }


}

//    private fun initView() {
//        txtForgotPassword.setOnClickListener(this)
//        btnSign.setOnClickListener(this)
//    }

//    override fun onClick(v: View?) {
//        when(v!!.id){
//            R.id.txtForgotPassword -> {
//                val intent = Intent(this, ForgotPasswordActivity::class.java)
//                startActivity(intent)
//            }
//            R.id.btnSign -> {
//                val intent = Intent(this, NavigationActivity::class.java)
//                startActivity(intent)
//            }
//
//        }
//    }
