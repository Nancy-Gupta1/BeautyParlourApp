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
import kotlinx.android.synthetic.main.activity_hair_cut.HairCutrecyclerView
import kotlinx.android.synthetic.main.activity_more.*
import kotlinx.android.synthetic.main.custom_toolbar.*
import kotlinx.android.synthetic.main.item_haircut_adapter.view.*
import kotlinx.android.synthetic.main.item_more_adapter.view.*

class MoreActivity : AppCompatActivity() {
    var mRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more)
        val toolbar: Toolbar = findViewById(R.id.toolbar1)
        setSupportActionBar(toolbar)
        toolbarName.text = "More"
        initView()
    }

    private fun initView() {
        menu_btn.setOnClickListener {
            onBackPressed()
        }
        mRef = FirebaseDatabase.getInstance().getReference("more")
        moreRecyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        val firebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<MakeUpModel, ViewHolder>(
            MakeUpModel::class.java,R.layout.item_more_adapter,
            ViewHolder::class.java,mRef){
            override fun populateViewHolder(p0: ViewHolder?, p1: MakeUpModel?, p2: Int) {
                p0?.itemView?.morecrossImage?.setOnClickListener {
                    selecteUser(p1?.name, "")

                }
                Glide.with(this@MoreActivity).load(p1?.url).into(p0?.itemView!!.moreImage)
                p0.itemView.moreName?.text = p1?.name
                p0.itemView.txtmoreName?.text = p1?.name
                p0.itemView.txtmore?.text = p1?.title
                p0.itemView.txtmore?.setOnClickListener {
                    startActivity(Intent(this@MoreActivity, AppoinmentActivity:: class.java))

                }
            }

        }
        moreRecyclerView.adapter = firebaseRecyclerAdapter


    }
    private fun selecteUser(name: String?, s: String) {

        val Threading = "Threading"
        val Waxing = "Waxing"
        val Manicure = "Manicure"
        val Pedicure = "Pedicure"
        val Makeup = "Makeup and Styling"

        if(Threading.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsThreadingActivity:: class.java))
        }
        else if (Waxing.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsWaxingActivity:: class.java))

        }
        else if (Manicure.contentEquals(name.toString())){
            startActivity(Intent(this, DetailManicureActivity:: class.java))

        }
        else if (Pedicure.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsPedicureActivity:: class.java))

        } else if(Makeup.contentEquals(name.toString())){
            startActivity(Intent(this, DetailsMakeupandStylingActivity:: class.java))
        }

    }

}