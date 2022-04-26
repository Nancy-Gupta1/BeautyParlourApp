package com.beautyparlour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.beautyparlour.models.MakeUpModel
import com.beautyparlour.models.OurBrandsModel
import com.beautyparlour.utils.ViewHolder
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_hair_cut.*
import kotlinx.android.synthetic.main.activity_hair_cut.HairCutrecyclerView
import kotlinx.android.synthetic.main.activity_hair_facial.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.custom_toolbar.*
import kotlinx.android.synthetic.main.item_facial_adapter.view.*
import kotlinx.android.synthetic.main.item_haircut_adapter.view.*

class HairFacialActivity : AppCompatActivity() {
    var mRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hair_facial)
        val toolbar: Toolbar = findViewById(R.id.toolbar1)
        setSupportActionBar(toolbar)
        toolbarName.text = "Facial"
        initView()
    }

    private fun initView() {
        menu_btn.setOnClickListener {
            onBackPressed()
        }
        mRef = FirebaseDatabase.getInstance().getReference("facial")
        facialRecyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        val firebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<MakeUpModel, ViewHolder>(
            MakeUpModel::class.java,R.layout.item_facial_adapter,
            ViewHolder::class.java,mRef){
            override fun populateViewHolder(p0: ViewHolder?, p1: MakeUpModel?, p2: Int) {
                p0?.itemView?.facialCrossImage?.setOnClickListener {
                    selecteUser(p1?.name, "")
                }
                 Glide.with(this@HairFacialActivity).load(p1?.url).into(p0?.itemView!!.facialImage)
                p0.itemView.facialName?.text = p1?.name
                p0.itemView.txtfacialappoint?.text = p1?.title
                p0.itemView.txtfacialappoint.setOnClickListener {
                    startActivity(Intent(this@HairFacialActivity, AppoinmentActivity:: class.java))

                }
                p0.itemView.txtfacialName?.text = p1?.name


            }

        }
        facialRecyclerView.adapter = firebaseRecyclerAdapter
    }

    private fun selecteUser(name: String?, s: String) {
        val Diamond = "Diamond facial"
        val Aromatherapy = "Aromatherapy facial"
        val Collagen = "Collagen facial"
        val Photo = "Photo facial"
        val Gold = "Gold radiance facial"

        if(Diamond.contentEquals(name.toString())){
            startActivity(Intent(this, DetailDiamondActivity:: class.java))
        }
        else if (Aromatherapy.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsAromatherapyActivity:: class.java))

        }
        else if (Collagen.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsCollagenActivity:: class.java))

        }
        else if (Photo.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsPhotoActivity:: class.java))

        } else if(Gold.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsGoldActivity:: class.java))
        }
    }
}