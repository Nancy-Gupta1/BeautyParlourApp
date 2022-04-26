package com.beautyparlour.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.beautyparlour.*
import com.beautyparlour.models.HairSpecialistModel
import com.beautyparlour.models.TipsModel
import com.beautyparlour.utils.ViewHolder
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_tips.view.*
import kotlinx.android.synthetic.main.item_adapter_linearview.view.*
import kotlinx.android.synthetic.main.item_tips_adapter.view.*

class TipsFragment : Fragment() {
    var mRef: DatabaseReference? = null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragmen
        val view = inflater.inflate(R.layout.fragment_tips, container, false)
        initview(view)
        return view
    }

    private fun initview(view: View?) {
        mRef = FirebaseDatabase.getInstance().getReference("tips")
        view?.linearTipsRecyclerView?.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,false)
        val firebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<TipsModel, ViewHolder>(
            TipsModel::class.java, R.layout.item_tips_adapter,
            ViewHolder::class.java,mRef){
            override fun populateViewHolder(p0: ViewHolder?, p1: TipsModel?, p2: Int) {
                p0?.itemView?.cardviewTips?.setOnClickListener {
                    startActivity(Intent(context, DetailKhusiActivity:: class.java))

                    //selecteUser(p1?.name, "")

                }
                Glide.with(activity!!).load(p1?.url).into(p0?.itemView!!.tipsimageView)
                p0.itemView.txttipsname.text = p1?.name
                p0.itemView.txttipsLineartitle.text = p1?.title
            }
        }
          view?.linearTipsRecyclerView?.adapter = firebaseRecyclerAdapter

    }

//    private fun selecteUser(name: String?, s: String) {
//        val Khusi = "Khusi"
//        val Neha = "Neha"
//        val Nancy = "Nancy"
//        val Simran = "Simran"
//        val Nikita = "Nikita"
//        val Apoorva = "Apoorva"
//        val Krishna = "Krishna"
//        val Kanchan = "Kanchan"
//        val Rupali = "Rupali"
//        val Deepali = "Deepali"
//        val Kirti = "Kirti"
//
//
//
//        if(Khusi.contentEquals(name.toString())){
//            startActivity(Intent(context, DetailKhusiActivity:: class.java))
//        }
//        else if (Neha.contentEquals(name.toString())){
//            startActivity(Intent(context, DetailKhusiActivity:: class.java))
//
//        }
//        else if (Nancy.contentEquals(name.toString())){
//            startActivity(Intent(context, DetailNancyActivity:: class.java))
//
//        }
//        else if (Simran.contentEquals(name.toString())){
//            startActivity(Intent(context, DetailSimranActivity:: class.java))
//
//        }
//        else if (Nikita.contentEquals(name.toString())){
//            startActivity(Intent(context, DetailNikitaActivity:: class.java))
//
//        }
//        else if (Apoorva.contentEquals(name.toString())){
//            startActivity(Intent(context, DetailApoorvaActivity:: class.java))
//
//        }
//
//        else if (Krishna.contentEquals(name.toString())){
//            startActivity(Intent(context, DetailKrishnaActivity:: class.java))
//
//        }
//
//        else if (Rupali.contentEquals(name.toString())){
//            startActivity(Intent(context, DetailRupaliActivity:: class.java))
//
//        }
//        else if (Deepali.contentEquals(name.toString())){
//            startActivity(Intent(context, DetailDeepaliActivity:: class.java))
//
//        }
//        else if (Kirti.contentEquals(name.toString())){
//            startActivity(Intent(context, DetailKirtiActivity:: class.java))
//
//        }
//    }
}