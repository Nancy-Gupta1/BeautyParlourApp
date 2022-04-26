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
import kotlinx.android.synthetic.main.activity_hair_spa.*
import kotlinx.android.synthetic.main.activity_make_up.*
import kotlinx.android.synthetic.main.activity_make_up.MakeuprecyclerView
import kotlinx.android.synthetic.main.custom_toolbar.*
import kotlinx.android.synthetic.main.item_hairspa_adapter.view.*
import kotlinx.android.synthetic.main.item_makeup_adapter.view.*
import kotlinx.android.synthetic.main.item_makeup_adapter.view.MakeupName

class HairSpaActivity : AppCompatActivity() {

    var mRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hair_spa)
        val toolbar: Toolbar = findViewById(R.id.toolbar1)
        setSupportActionBar(toolbar)
        toolbarName.text = "Hair Spa"
        initView()
    }

    private fun initView() {
        menu_btn.setOnClickListener {
            onBackPressed()
        }
        mRef = FirebaseDatabase.getInstance().getReference("hairspa")
        HairSparecyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        val firebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<OurBrandsModel, ViewHolder>(
            OurBrandsModel::class.java,R.layout.item_hairspa_adapter,
            ViewHolder::class.java,mRef){
            override fun populateViewHolder(p0: ViewHolder?, p1: OurBrandsModel?, p2: Int) {

                p0?.itemView?.hairspacrossImage?.setOnClickListener {
                    selecteUser(p1?.name, "")

                }
                Glide.with(this@HairSpaActivity).load(p1?.url).into(p0?.itemView!!.hairspaImage)
                p0.itemView.hairspaName?.text = p1?.name
                p0.itemView.txthairspaName?.text = p1?.name
                p0.itemView.txthairspaappoint?.text = p1?.title
                p0.itemView.txthairspaappoint?.setOnClickListener {
                    startActivity(Intent(this@HairSpaActivity, AppoinmentActivity:: class.java))
                }

            }

        }
        HairSparecyclerView.adapter = firebaseRecyclerAdapter
   }

    private fun selecteUser(name: String?, s: String) {
        val smoothening = "hair smoothening"
        val coloured = "coloured hair"
        val scalp = "oily scalp"
        val dandruff = "Anti-dandruff"
        val itchy = "itchy scalp"

        if(smoothening.contentEquals(name.toString())){
            startActivity(Intent(this, DetailHairSmootheningActivity:: class.java))
        }
        else if (coloured.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsColouredHairActivity:: class.java))

        }
        else if (scalp.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsOilyScalpActivity:: class.java))

        }
        else if (dandruff.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsAntiDandruffActivity:: class.java))

        } else if(itchy.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsItchyScalpActivity:: class.java))
        }
    }


}