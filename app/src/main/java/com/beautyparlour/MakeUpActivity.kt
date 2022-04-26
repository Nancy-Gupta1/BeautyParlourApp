package com.beautyparlour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.beautyparlour.models.MakeUpModel
import com.beautyparlour.utils.ViewHolder
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_make_up.*
import kotlinx.android.synthetic.main.custom_toolbar.*
import kotlinx.android.synthetic.main.item_makeup_adapter.*
import kotlinx.android.synthetic.main.item_makeup_adapter.view.*
import kotlinx.android.synthetic.main.item_ourbrands_adapter.view.*

class MakeUpActivity : AppCompatActivity() {
    var mRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_up)
        val toolbar: Toolbar = findViewById(R.id.toolbar1)
        setSupportActionBar(toolbar)
        toolbarName.text = "Make Up"
        initView()
    }

    private fun initView() {
        menu_btn.setOnClickListener {
            onBackPressed()
        }
        mRef = FirebaseDatabase.getInstance().getReference("makeup")
        MakeuprecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        val firebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<MakeUpModel,ViewHolder>(MakeUpModel::class.java,R.layout.item_makeup_adapter,ViewHolder::class.java,mRef){
            override fun populateViewHolder(p0: ViewHolder?, p1: MakeUpModel?, p2: Int) {
                p0?.itemView?.crossImage?.setOnClickListener {

                    selecteUser(p1?.name, "")
//                    val intent = Intent(this@MakeUpActivity, DetailMakeUpActivity::class.java)
//                    intent.putExtra("Image",p1?.url)
//                     startActivity(intent)

                }
                Glide.with(this@MakeUpActivity).load(p1?.url).into(p0?.itemView!!.MakeupImage)
                p0.itemView.MakeupName?.text = p1?.name
                p0.itemView.txtappoint?.text = p1?.title
                p0.itemView.txtMakeupName.text = p1?.name
                p0.itemView.txtappoint.setOnClickListener {
                    startActivity(Intent(this@MakeUpActivity, AppoinmentActivity:: class.java))
                }

            }

        }
        MakeuprecyclerView.adapter = firebaseRecyclerAdapter

    }

    private fun selecteUser(name: String?, s: String) {

        val makeup = "Bridal make up"
        val engagementmakeup = "Engagement make up"
        val reception = "Reception make up"
        val eyemakeup = "Eye make up"
        val lips = "Lips"

        if(makeup.contentEquals(name.toString())){
            startActivity(Intent(this, DetailMakeUpActivity:: class.java))
        }
        else if (engagementmakeup.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsEngagementActivity:: class.java))

        }
        else if (reception.contentEquals(name.toString())){
            startActivity(Intent(this, DetailReceptionActivity:: class.java))

        }
        else if (eyemakeup.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsEyeActivity:: class.java))

        } else if(lips.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsLipsActivity:: class.java))
        }

    }
}