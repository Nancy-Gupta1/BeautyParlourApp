package com.beautyparlour.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.beautyparlour.MapsActivity
import com.beautyparlour.R
import com.beautyparlour.models.ContactModel
import com.beautyparlour.models.HairSpecialistModel
import com.beautyparlour.utils.ViewHolder
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.fragment_about.view.*
import kotlinx.android.synthetic.main.fragment_expert.view.*
import kotlinx.android.synthetic.main.fragment_expert.view.NailArtistRecyclerView
import kotlinx.android.synthetic.main.item_adapter_linearview.view.*

//https://www.youtube.com/watch?v=7Vk2BPIOa6g
//location
class AboutFragment : Fragment() {
    var mRef: DatabaseReference? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_about, container, false)
        initView(view)
        contactView(view)
        view.txtlocation.setOnClickListener {
             startActivity(Intent(context, MapsActivity::class.java))
        }
        return view
    }

    private fun contactView(view: View?) {
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("about")
        ref.addValueEventListener(object  : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val contactlist = snapshot.getValue(ContactModel::class.java)
                view?.txtmobile?.text = contactlist?.mobile
                view?.txtemail?.text = contactlist?.email
                view?.txttiming?.text = contactlist?.time
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context,error.code, Toast.LENGTH_SHORT).show()
            }

        })


    }

    private fun initView(view: View?) {
        mRef = FirebaseDatabase.getInstance().getReference("hairspecialist")
        view?.AboutRecyclerView?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        val firebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<HairSpecialistModel, ViewHolder>(
            HairSpecialistModel::class.java, R.layout.item_adapter_linearview,
            ViewHolder::class.java,mRef){
            override fun populateViewHolder(p0: ViewHolder?, p1: HairSpecialistModel?, p2: Int) {
                Glide.with(activity!!).load(p1?.image).into(p0?.itemView!!.imglinear)
                p0.itemView.txtLinearName.text = p1?.name
            }

        }
        view?.AboutRecyclerView?.adapter = firebaseRecyclerAdapter

    }

}