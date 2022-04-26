package com.beautyparlour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.beautyparlour.models.OurBrandsModel
import com.beautyparlour.utils.ViewHolder
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_hair_color.*
import kotlinx.android.synthetic.main.activity_hair_color.HairColorerecyclerView
import kotlinx.android.synthetic.main.activity_nail_art.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.custom_toolbar.*
import kotlinx.android.synthetic.main.item_haircolor_adapter.view.*
import kotlinx.android.synthetic.main.item_haircolor_adapter.view.haircolorcrossImage
import kotlinx.android.synthetic.main.item_nailart_adapter.view.*

class NailArtActivity : AppCompatActivity() {
    var mRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nail_art)
        val toolbar: Toolbar = findViewById(R.id.toolbar1)
        setSupportActionBar(toolbar)
        toolbarName.text = "Nail Art"
        initView()
    }

    private fun initView() {
        menu_btn.setOnClickListener {
            onBackPressed()
        }
        mRef = FirebaseDatabase.getInstance().getReference("nailart")
        nailArtrecyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        val firebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<OurBrandsModel, ViewHolder>(
            OurBrandsModel::class.java,R.layout.item_nailart_adapter,
            ViewHolder::class.java,mRef){
            override fun populateViewHolder(p0: ViewHolder?, p1: OurBrandsModel?, p2: Int) {

                p0?.itemView?.nailArtcrossImage?.setOnClickListener {
                    selecteUser(p1?.name, "")

                }


                 Glide.with(this@NailArtActivity).load(p1?.url).into(p0?.itemView!!.nailArtImage)
                p0.itemView.txtnailArtName?.text = p1?.name
                p0.itemView.txtnailappoint?.text = p1?.title
                p0.itemView.txtnailappoint.setOnClickListener {
                    startActivity(Intent(this@NailArtActivity, AppoinmentActivity:: class.java))

                }
                p0.itemView.nailArtName?.text = p1?.name
            }

        }
        nailArtrecyclerView.adapter = firebaseRecyclerAdapter
    }


    private fun selecteUser(name: String?, s: String) {

        val Sponge = "Sponge Bobbing"
        val Stamping = "Stamping Looks Great"
        val Taping = "Taping is Not a Task"
        val Digi = "Digi World Nail Art"
        val Nail = "Decals and Stickers"

        if(Sponge.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsSpongeActivity:: class.java))
        }
        else if (Stamping.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsStampingActivity:: class.java))

        }
        else if (Taping.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsTapingActivity:: class.java))

        }
        else if (Digi.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsDigiActivity:: class.java))

        } else if(Nail.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsNailActivity:: class.java))
        }

    }


}