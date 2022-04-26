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
import kotlinx.android.synthetic.main.activity_hair_color.*
import kotlinx.android.synthetic.main.activity_hair_style.*
import kotlinx.android.synthetic.main.activity_hair_style.HairStylerecyclerView
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.custom_toolbar.*
import kotlinx.android.synthetic.main.item_haircolor_adapter.*
import kotlinx.android.synthetic.main.item_haircolor_adapter.view.*
import kotlinx.android.synthetic.main.item_haircut_adapter.view.*
import kotlinx.android.synthetic.main.item_hairstyle_adapter.view.*

class HairColorActivity : AppCompatActivity() {
    var mRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hair_color)
        val toolbar: Toolbar = findViewById(R.id.toolbar1)
        setSupportActionBar(toolbar)
        toolbarName.text = getString(R.string.action_haircolor)
        initView()
    }

    private fun initView() {
        menu_btn.setOnClickListener {
            onBackPressed()
        }
        mRef = FirebaseDatabase.getInstance().getReference("haircolors")
        HairColorerecyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        val firebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<MakeUpModel, ViewHolder>(
            MakeUpModel::class.java,R.layout.item_haircolor_adapter,
            ViewHolder::class.java,mRef){
            override fun populateViewHolder(p0: ViewHolder?, p1: MakeUpModel?, p2: Int) {
                p0?.itemView?.haircolorcrossImage?.setOnClickListener {
                    selecteUser(p1?.name, "")

                }


                Glide.with(this@HairColorActivity).load(p1?.url).into(p0?.itemView!!.haircolorImage)
                p0.itemView.haircolorName?.text = p1?.name
                p0.itemView.txthaircolorName?.text = p1?.name
                p0.itemView.txthaircolorappoint?.text = p1?.title
                p0.itemView.txthaircolorappoint.setOnClickListener {
                    startActivity(Intent(this@HairColorActivity, AppoinmentActivity:: class.java))

                }
                //p0?.itemView?.txthairStyleappoint?.setText(p1?.title)
            }

        }
        HairColorerecyclerView.adapter = firebaseRecyclerAdapter
    }

    private fun selecteUser(name: String?, s: String) {

        val LowLights = "LittleLow"
        val HighLights = "HighLights"
        val Ombre = "Ombre"
        val Balayage = "Balayage"
        val Permanent = "Permanent hair color"

        if(LowLights.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsLowLightsActivity:: class.java))
        }
        else if (HighLights.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsHighLightsActivity:: class.java))

        }
        else if (Ombre.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsOmbreActivity:: class.java))

        }
        else if (Balayage.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsBalayageActivity:: class.java))

        } else if(Permanent.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsPermanentActivity:: class.java))
        }

    }
}