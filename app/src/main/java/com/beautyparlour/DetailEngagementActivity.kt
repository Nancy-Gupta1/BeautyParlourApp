package com.beautyparlour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.beautyparlour.models.OurBrandsModel
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detail_engagement.*
import kotlinx.android.synthetic.main.activity_details_bride.*
import kotlinx.android.synthetic.main.activity_details_bride.brideDetailsName
import kotlinx.android.synthetic.main.activity_details_bride.brideName
import kotlinx.android.synthetic.main.activity_details_bride.txtbride
import kotlinx.android.synthetic.main.custom_toolbar.*

class DetailEngagementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_engagement)
        val toolbar: Toolbar = findViewById(R.id.toolbar1)
        setSupportActionBar(toolbar)
        toolbarName.text = "Engagement"
        initView()
    }

    private fun initView() {
        menu_btn.setOnClickListener {
            onBackPressed()

        }

        EngagementDetails()
    }

        private fun EngagementDetails() {
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("EngagementDetails")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val contactlist = snapshot.getValue(OurBrandsModel::class.java)
                EngagementName.text = contactlist?.name
                EngagementDetailsName.text = contactlist?.title
                Glide.with(this@DetailEngagementActivity).load(contactlist?.url).into(EngagementImage)
                txtEngagementappoint.setOnClickListener {
                    startActivity(Intent(this@DetailEngagementActivity, AppoinmentActivity:: class.java))
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}