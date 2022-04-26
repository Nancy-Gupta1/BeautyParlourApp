package com.beautyparlour.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.beautyparlour.*
import com.beautyparlour.models.ServiceModel
import com.beautyparlour.utils.ViewHolder
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_sevice.view.*
import kotlinx.android.synthetic.main.item_adater_glide.view.*
import kotlinx.android.synthetic.main.item_service_adapter.view.*

class SeviceFragment : Fragment() {
    var mRef: DatabaseReference? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sevice, container, false)
//        mRef = FirebaseDatabase.getInstance().getReference("services")
//        view?.gridServicRecyclerView?.layoutManager = GridLayoutManager(context, 2)
         linearView(view)

        return view
    }


    private fun linearView(view: View?) {
        mRef = FirebaseDatabase.getInstance().getReference("services")
        view?.gridServicRecyclerView?.layoutManager = GridLayoutManager(context, 2)
        val firebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<ServiceModel, ViewHolder>(
            ServiceModel::class.java, R.layout.item_service_adapter,
            ViewHolder::class.java, mRef
        ) {
            override fun populateViewHolder(p0: ViewHolder?, p1: ServiceModel?, p2: Int) {
                p0?.itemView?.linearLayout?.setOnClickListener {
                      selecteUser(p1?.name, "")


                    //startActivity(Intent(context, MakeUpActivity:: class.java))
                }
                Glide.with(activity!!).load(p1?.url).into(p0?.itemView!!.bgImageGrid)
                Glide.with(activity!!).load(p1?.image).into(p0.itemView.gridImageIcon)
                p0.itemView.txtGridName.text = p1?.name

            }

            private fun selecteUser(name: String?, s: String) {
                val makeup = "Make Up"
                val haircut = "Hair cut"
                val hairstyle = "Hair Style"
                val hairspa = "Hair Spa"
                val hairfacial = "Facial"
                val haircolor = "Hair color"
                val nailart = "Nail art"
                val more = "More"

                if(makeup.contentEquals(name.toString())){
                    startActivity(Intent(context, MakeUpActivity:: class.java))
                }
                else if (haircut.contentEquals(name.toString())){
                    startActivity(Intent(context, HairCutActivity:: class.java))

                }
                else if (hairstyle.contentEquals(name.toString())){
                    startActivity(Intent(context, HairStyleActivity:: class.java))

                }
                else if (hairspa.contentEquals(name.toString())){
                    startActivity(Intent(context, HairSpaActivity:: class.java))

                }
                else if (hairfacial.contentEquals(name.toString())){
                    startActivity(Intent(context, HairFacialActivity:: class.java))

                }
                else if (haircolor.contentEquals(name.toString())){
                    startActivity(Intent(context, HairColorActivity:: class.java))

                }
                else if (nailart.contentEquals(name.toString())){
                    startActivity(Intent(context, NailArtActivity:: class.java))

                }else{
                    startActivity(Intent(context, MoreActivity:: class.java))
                }

            }
        }
        view?.gridServicRecyclerView?.adapter = firebaseRecyclerAdapter

    }
}