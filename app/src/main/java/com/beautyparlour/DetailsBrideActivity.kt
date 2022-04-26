package com.beautyparlour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.beautyparlour.models.OurBrandsModel
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detail_haldi.*
import kotlinx.android.synthetic.main.activity_detail_haldi.haldiDetailsName
import kotlinx.android.synthetic.main.activity_detail_haldi.haldiImage
import kotlinx.android.synthetic.main.activity_detail_haldi.haldiName
import kotlinx.android.synthetic.main.activity_detail_haldi.txthaldi
import kotlinx.android.synthetic.main.activity_details_bride.*
import kotlinx.android.synthetic.main.custom_toolbar.*

class DetailsBrideActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_bride)
        val toolbar: Toolbar = findViewById(R.id.toolbar1)
        setSupportActionBar(toolbar)
        toolbarName.text = "Bride"
        initView()
    }

    private fun initView() {
        menu_btn.setOnClickListener {
            onBackPressed()

        }

        BrideDetails()


    }

    private fun BrideDetails() {
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("BrideDetails")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val contactlist = snapshot.getValue(OurBrandsModel::class.java)
                brideName.text = contactlist?.name
                brideDetailsName.text = contactlist?.title
                Glide.with(this@DetailsBrideActivity).load(contactlist?.url).into(brideImage)
                txtbride.setOnClickListener {
                    startActivity(Intent(this@DetailsBrideActivity, AppoinmentActivity:: class.java))
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}