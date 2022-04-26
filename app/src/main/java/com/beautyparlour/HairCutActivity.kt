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
import kotlinx.android.synthetic.main.activity_hair_cut.*
import kotlinx.android.synthetic.main.activity_make_up.*
import kotlinx.android.synthetic.main.custom_toolbar.*
import kotlinx.android.synthetic.main.item_haircut_adapter.*
import kotlinx.android.synthetic.main.item_haircut_adapter.view.*
import kotlinx.android.synthetic.main.item_makeup_adapter.view.*
import kotlinx.android.synthetic.main.item_makeup_adapter.view.MakeupName

class HairCutActivity : AppCompatActivity() {
    var mRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hair_cut)
         val toolbar: Toolbar = findViewById(R.id.toolbar1)
        setSupportActionBar(toolbar)
        toolbarName.text = "Hair Cut"
        initView()
    }

    private fun initView() {
        menu_btn.setOnClickListener {
            onBackPressed()
        }
        mRef = FirebaseDatabase.getInstance().getReference("haircut")
        HairCutrecyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        val firebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<MakeUpModel, ViewHolder>(
            MakeUpModel::class.java,R.layout.item_haircut_adapter,
            ViewHolder::class.java,mRef){
            override fun populateViewHolder(p0: ViewHolder?, p1: MakeUpModel?, p2: Int) {
                p0?.itemView?.hairCutCrossImage?.setOnClickListener {
                        selecteUser(p1?.name, "")

                }
                Glide.with(this@HairCutActivity).load(p1?.url).into(p0?.itemView!!.hairCutImage)
                p0.itemView.hairCutName?.text = p1?.name
                p0.itemView.txthairCutappoint?.text = p1?.title
                p0.itemView.txthairCutName?.text = p1?.name
                p0.itemView.txthairCutappoint.setOnClickListener {
                    startActivity(Intent(this@HairCutActivity, AppoinmentActivity:: class.java))
                }

            }

        }
        HairCutrecyclerView.adapter = firebaseRecyclerAdapter


    }

    private fun selecteUser(name: String?, s: String) {

        val straight = "Straight hair cut"
        val ucut = "U cut"
        val vcut = "V cut"
        val layers = "Layers cut"
        val feather = "Feather cut"

        if(straight.contentEquals(name.toString())){
            startActivity(Intent(this, DetailStraightActivity:: class.java))
        }
        else if (ucut.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsUCutActivity:: class.java))

        }
        else if (vcut.contentEquals(name.toString())){
            startActivity(Intent(this, DetailVCutActivity:: class.java))

        }
        else if (layers.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsLayersActivity:: class.java))

        } else if(feather.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsFeatherActivity:: class.java))
        }

    }
}