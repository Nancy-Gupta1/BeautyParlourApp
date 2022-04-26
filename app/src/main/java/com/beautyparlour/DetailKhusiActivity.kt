package com.beautyparlour

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.beautyparlour.models.ContactModel
import com.beautyparlour.models.OurBrandsModel
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detail_khusi.*
import kotlinx.android.synthetic.main.custom_toolbar.*
import kotlinx.android.synthetic.main.item_adapter_linearview.view.*
import kotlinx.android.synthetic.main.item_makeup_adapter.view.*

class DetailKhusiActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_khusi)
        val toolbar: Toolbar = findViewById(R.id.toolbar1)
        setSupportActionBar(toolbar)
        toolbarName.text = "Tips"
        initView()
    }

    private fun initView() {
        menu_btn.setOnClickListener {
            onBackPressed()
        }

        KhusiDetails()
        NehaDetails()
        NancyDetails()
        SimranDetails()
        NikitaDetails()
        ApoorvaDetails()
        KrishnaDetails()
        KanchanDetails()
        RupaliDetails()
        DeepaliDetails()
        KirtiDetails()

    }

    private fun DeepaliDetails() {
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("deepalitips")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val contactlist = snapshot.getValue(OurBrandsModel::class.java)
                tipsName.text = contactlist?.name
                detailsName.text = contactlist?.title
                Glide.with(this@DetailKhusiActivity).load(contactlist?.url).into(tipsImage)

            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private fun ApoorvaDetails() {
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("apoorvatips")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val contactlist = snapshot.getValue(OurBrandsModel::class.java)
                tipsName.text = contactlist?.name
                detailsName.text = contactlist?.title

                Glide.with(this@DetailKhusiActivity).load(contactlist?.url).into(tipsImage)


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun KirtiDetails() {
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("kirti")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val contactlist = snapshot.getValue(OurBrandsModel::class.java)
                tipsName.text = contactlist?.name
                detailsName.text = contactlist?.title

                Glide.with(this@DetailKhusiActivity).load(contactlist?.url).into(tipsImage)


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun RupaliDetails() {
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("rupalitips")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val contactlist = snapshot.getValue(OurBrandsModel::class.java)
                tipsName.text = contactlist?.name
                detailsName.text = contactlist?.title

                Glide.with(this@DetailKhusiActivity).load(contactlist?.url).into(tipsImage)


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun NikitaDetails() {
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("nikitatips")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val contactlist = snapshot.getValue(OurBrandsModel::class.java)
                tipsName.text = contactlist?.name
                detailsName.text = contactlist?.title

                Glide.with(this@DetailKhusiActivity).load(contactlist?.url).into(tipsImage)


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun NancyDetails() {
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("nancytips")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val contactlist = snapshot.getValue(OurBrandsModel::class.java)
                tipsName.text = contactlist?.name
                detailsName.text = contactlist?.title

                Glide.with(this@DetailKhusiActivity).load(contactlist?.url).into(tipsImage)


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun SimranDetails() {
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("simrantips")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val contactlist = snapshot.getValue(OurBrandsModel::class.java)
                tipsName.text = contactlist?.name
                detailsName.text = contactlist?.title

                Glide.with(this@DetailKhusiActivity).load(contactlist?.url).into(tipsImage)


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun KanchanDetails() {
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("kanchantips")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val contactlist = snapshot.getValue(OurBrandsModel::class.java)
                tipsName.text = contactlist?.name
                detailsName.text = contactlist?.title

                Glide.with(this@DetailKhusiActivity).load(contactlist?.url).into(tipsImage)


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun KrishnaDetails() {
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("Krishnatips")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val contactlist = snapshot.getValue(OurBrandsModel::class.java)
                tipsName.text = contactlist?.name
                detailsName.text = contactlist?.title

                Glide.with(this@DetailKhusiActivity).load(contactlist?.url).into(tipsImage)


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun NehaDetails() {
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("nehatips")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val contactlist = snapshot.getValue(OurBrandsModel::class.java)
                tipsName.text = contactlist?.name
                detailsName.text = contactlist?.title

                Glide.with(this@DetailKhusiActivity).load(contactlist?.url).into(tipsImage)


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

    private fun KhusiDetails() {
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("khusitips")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val contactlist = snapshot.getValue(OurBrandsModel::class.java)
                tipsName.text = contactlist?.name
                detailsName.text = contactlist?.title
                Glide.with(this@DetailKhusiActivity).load(contactlist?.url).into(tipsImage)


            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}