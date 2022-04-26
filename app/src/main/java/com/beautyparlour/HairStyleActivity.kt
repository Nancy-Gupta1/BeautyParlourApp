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
import kotlinx.android.synthetic.main.activity_hair_style.*
import kotlinx.android.synthetic.main.activity_make_up.*
import kotlinx.android.synthetic.main.activity_make_up.MakeuprecyclerView
import kotlinx.android.synthetic.main.custom_toolbar.*
import kotlinx.android.synthetic.main.item_hairstyle_adapter.view.*
import kotlinx.android.synthetic.main.item_makeup_adapter.view.*
import kotlinx.android.synthetic.main.item_makeup_adapter.view.MakeupName

class HairStyleActivity : AppCompatActivity() {
    var mRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hair_style)
        val toolbar: Toolbar = findViewById(R.id.toolbar1)
        setSupportActionBar(toolbar)
        toolbarName.text = getString(R.string.action_hairStyle)
        initView()
    }

    private fun initView() {
        menu_btn.setOnClickListener {
            onBackPressed()
        }
        mRef = FirebaseDatabase.getInstance().getReference("hairstyle")
        HairStylerecyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        val firebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<MakeUpModel, ViewHolder>(
            MakeUpModel::class.java,R.layout.item_hairstyle_adapter,
            ViewHolder::class.java,mRef){
            override fun populateViewHolder(p0: ViewHolder?, p1: MakeUpModel?, p2: Int) {
                p0?.itemView?.hairStyleCrossImage?.setOnClickListener {
                    selecteUser(p1?.name, "")

                }
                Glide.with(this@HairStyleActivity).load(p1?.url).into(p0?.itemView!!.hairStyleImage)
                p0.itemView.hairStyleName?.text = p1?.name
                p0.itemView.txthairStyleappoint?.text = p1?.title
                p0.itemView.txthairStyleName.text = p1?.name
                p0.itemView.txthairStyleappoint.setOnClickListener {
                    startActivity(Intent(this@HairStyleActivity, AppoinmentActivity:: class.java))
                }
            }

        }
        HairStylerecyclerView.adapter = firebaseRecyclerAdapter
    }

    private fun selecteUser(name: String?, s: String) {

        val twisted = "Twisted side Bun"
        val curls = "Curls and flowers"
        val voluminous = "Voluminous Bun"
        val bouquet = "Bouquet Bun"
        val mermaid = "Mermaid FrenchBraid"

        if(twisted.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsTwistedActivity:: class.java))
        }
        else if (curls.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsCurlsActivity:: class.java))

        }
        else if (voluminous.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsVoluminousActivity:: class.java))

        }
        else if (bouquet.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsBouquetActivity:: class.java))

        } else if(mermaid.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsMermaidActivity:: class.java))
        }

    }

}