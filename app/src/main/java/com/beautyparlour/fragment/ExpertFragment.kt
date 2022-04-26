package com.beautyparlour.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.beautyparlour.R
import com.beautyparlour.models.HairSpecialistModel
import com.beautyparlour.utils.ViewHolder
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_expert.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_home.view.linearRecyclerView
import kotlinx.android.synthetic.main.item_adapter_linearview.view.*


class ExpertFragment : Fragment() {
    var mRef: DatabaseReference? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_expert, container, false)
         hairStyleView(view)
        nailArtView(view)
        initView(view)
        return view
    }

    private fun nailArtView(view: View?) {
        mRef = FirebaseDatabase.getInstance().getReference("hairspecialist")
        view?.NailArtistRecyclerView?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        val firebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<HairSpecialistModel, ViewHolder>(
            HairSpecialistModel::class.java, R.layout.item_adapter_linearview,
            ViewHolder::class.java,mRef){
            override fun populateViewHolder(p0: ViewHolder?, p1: HairSpecialistModel?, p2: Int) {
                Glide.with(activity!!).load(p1?.image).into(p0?.itemView!!.imglinear)
                p0.itemView.txtLinearName.text = p1?.name
            }

        }
        view?.NailArtistRecyclerView?.adapter = firebaseRecyclerAdapter

    }

    private fun hairStyleView(view: View?) {
        mRef = FirebaseDatabase.getInstance().getReference("hairspecialist")
        view?.hairStyleArtistRecyclerView?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        val firebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<HairSpecialistModel, ViewHolder>(
            HairSpecialistModel::class.java, R.layout.item_adapter_linearview,
            ViewHolder::class.java,mRef){
            override fun populateViewHolder(p0: ViewHolder?, p1: HairSpecialistModel?, p2: Int) {
                Glide.with(activity!!).load(p1?.image).into(p0?.itemView!!.imglinear)
                p0.itemView.txtLinearName.text = p1?.name
            }

        }
        view?.hairStyleArtistRecyclerView?.adapter = firebaseRecyclerAdapter

    }

    private fun initView(view: View?) {
        mRef = FirebaseDatabase.getInstance().getReference("hairspecialist")
        view?.makeupArtistRecyclerView?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        val firebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<HairSpecialistModel, ViewHolder>(
            HairSpecialistModel::class.java, R.layout.item_adapter_linearview,
            ViewHolder::class.java,mRef){
            override fun populateViewHolder(p0: ViewHolder?, p1: HairSpecialistModel?, p2: Int) {
                Glide.with(activity!!).load(p1?.image).into(p0?.itemView!!.imglinear)
                p0.itemView.txtLinearName.text = p1?.name
            }

        }
        view?.makeupArtistRecyclerView?.adapter = firebaseRecyclerAdapter


    }


}