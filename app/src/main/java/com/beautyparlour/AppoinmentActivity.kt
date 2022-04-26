package com.beautyparlour

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import com.beautyparlour.models.AppoinmentModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_appoinment.*
import kotlinx.android.synthetic.main.activity_appoinment.edtAppDate
import kotlinx.android.synthetic.main.activity_appoinment.edtAppTime
import kotlinx.android.synthetic.main.activity_appoinment.edtAppUserName
import kotlinx.android.synthetic.main.activity_appoinment.txtAppoinment
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.custom_toolbar.*
import java.text.SimpleDateFormat
import java.util.*


class AppoinmentActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appoinment)
        val toolbar: Toolbar = findViewById(R.id.toolbar1)
        setSupportActionBar(toolbar)
        toolbarName.text = "Appointment"

        initView()
    }

    private fun initView() {
        menu_btn.setOnClickListener {
            onBackPressed()
        }
        val eusername = findViewById<EditText>(R.id.edtAppUserName)
        val ephone = findViewById<EditText>(R.id.edtAppUserphone)
        val eaddress = findViewById<EditText>(R.id.edtAppUseraddress)
        val edate = findViewById<ImageView>(R.id.dateImg)
        val etime = findViewById<ImageView>(R.id.timeImg)
        txtAppoinment.setOnClickListener {


            if (eusername.text.toString().isEmpty()) {
                edtAppUserName.error = "Palese enter valid username"
                edtAppUserName.requestFocus()


            } else if (ephone.text.toString().isEmpty()) {
                Toast.makeText(this, "Please enter 10 digit number", Toast.LENGTH_SHORT).show()
                edtAppUserphone.requestFocus()

            }else if (eaddress.text.toString().isEmpty()) {
                Toast.makeText(this, "Please enter valid address", Toast.LENGTH_SHORT).show()
                edtAppUseraddress.requestFocus()

            }
            else if (edtAppDate.text.toString().isEmpty()) {
                Toast.makeText(this, "Please select next date  ", Toast.LENGTH_SHORT).show()
                edtAppDate.requestFocus()

            }
            else if (edtAppTime.text.toString().isEmpty()) {
                Toast.makeText(this, "Please select time", Toast.LENGTH_SHORT).show()
                edtAppTime.requestFocus()

            }
            else {
                val ref: DatabaseReference =
                    FirebaseDatabase.getInstance().getReference("Appoinment")
                val userkey = ref.push().key
                val userdata = AppoinmentModel(
                    username = edtAppUserName.text.toString(),
                    userphone = edtAppUserphone.text.toString(),
                    useraddress = edtAppUseraddress.text.toString(),
                    userdate = edtAppDate.text.toString(),
                    usertime = edtAppTime.text.toString()
                )
                ref.child(userkey!!).setValue(userdata).addOnCompleteListener {
                    Toast.makeText(this, "message successfully send", Toast.LENGTH_LONG).show()
//                    edtAppUserName.setText(" ")
//                    edtAppDate.setText(" ")
//                    edtAppTime.setText(" ")
//                    edtAppUserphone.setText(" ")
//                    edtAppUseraddress.setText(" ")
                }
            }
        }

        edate.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{view, mYear, mMonth, mDay->
                edtAppDate.text = ""+ mDay +"/"+ month +"/"+ mYear
            }, year, month, day)
            dpd.show()
        }
        etime.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener{ timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                edtAppTime.text = SimpleDateFormat("HH:mm aa").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()

        }
    }
}