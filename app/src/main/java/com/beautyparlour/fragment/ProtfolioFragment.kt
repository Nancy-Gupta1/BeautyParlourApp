package com.beautyparlour.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.beautyparlour.*
import com.beautyparlour.models.HairSpecialistModel
import com.beautyparlour.models.ProtfolioModel
import com.beautyparlour.utils.ViewHolder
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_expert.view.*
import kotlinx.android.synthetic.main.fragment_expert.view.NailArtistRecyclerView
import kotlinx.android.synthetic.main.fragment_protfolio.view.*
import kotlinx.android.synthetic.main.item_adapter_linearview.view.*
import kotlinx.android.synthetic.main.item_adapter_linearview.view.imglinear
import kotlinx.android.synthetic.main.item_portfolio_adapter.view.*


class ProtfolioFragment : Fragment() {
    var mRef: DatabaseReference? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_protfolio, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View?) {
        mRef = FirebaseDatabase.getInstance().getReference("portfolio")
        view?.protfolioRecyclerView?.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        val firebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<ProtfolioModel, ViewHolder>(
            ProtfolioModel::class.java, R.layout.item_portfolio_adapter,
            ViewHolder::class.java,mRef){
            override fun populateViewHolder(p0: ViewHolder?, p1: ProtfolioModel?, p2: Int) {
                Glide.with(activity!!).load(p1?.url).into(p0?.itemView!!.imageProt)
                p0.itemView.namePort.text = p1?.name
                p0.itemView.portfolio.setOnClickListener {
                    selecteUser(p1?.name, "")
                }
            }



        }
        view?.protfolioRecyclerView?.adapter = firebaseRecyclerAdapter

    }


    private fun selecteUser(name: String?, s: String) {
        val Haldi = "Haldi"
        val Engagment = "Engagment"
        val Reception = "Reception"
        val Hairstyle = "Hair style"
        val Bride = "Bride"


        if (Haldi.contentEquals(name.toString())){
            startActivity(Intent(context, DetailHaldiActivity:: class.java))

        }else if (Bride.contentEquals(name.toString())){
            startActivity(Intent(context, DetailsBrideActivity:: class.java))

        }
        else if (Reception.contentEquals(name.toString())){
            startActivity(Intent(context, DetailReceptionActivity:: class.java))

        }
        else if (Hairstyle.contentEquals(name.toString())){
            startActivity(Intent(context, DetailsTwistedActivity:: class.java))

        }
        else if (Engagment.contentEquals(name.toString())){
            startActivity(Intent(context, DetailEngagementActivity:: class.java))

        }

    }

}