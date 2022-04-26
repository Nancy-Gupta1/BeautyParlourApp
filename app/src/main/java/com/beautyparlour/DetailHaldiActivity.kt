package com.beautyparlour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.beautyparlour.models.OurBrandsModel
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detail_haldi.*
import kotlinx.android.synthetic.main.activity_detail_khusi.*
import kotlinx.android.synthetic.main.activity_detail_khusi.detailsName
import kotlinx.android.synthetic.main.activity_detail_khusi.tipsImage
import kotlinx.android.synthetic.main.activity_detail_khusi.tipsName
import kotlinx.android.synthetic.main.custom_toolbar.*

class DetailHaldiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_haldi)
        val toolbar: Toolbar = findViewById(R.id.toolbar1)
        setSupportActionBar(toolbar)
        toolbarName.text = "Haldi"
        initView()
    }

    private fun initView() {
        menu_btn.setOnClickListener {
            onBackPressed()

        }

        HaldiDetails()

    }

    private fun HaldiDetails() {
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("HaldiDetails")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val contactlist = snapshot.getValue(OurBrandsModel::class.java)
                haldiName.text = contactlist?.name
                haldiDetailsName.text = contactlist?.title
                Glide.with(this@DetailHaldiActivity).load(contactlist?.url).into(haldiImage)
                txthaldi.setOnClickListener {
                    startActivity(Intent(this@DetailHaldiActivity, AppoinmentActivity:: class.java))
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

}