package com.beautyparlour.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.beautyparlour.R
import com.beautyparlour.models.ReviewModel
import com.beautyparlour.models.TipsModel
import com.beautyparlour.utils.ViewHolder
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_reviews.view.*
import kotlinx.android.synthetic.main.fragment_tips.view.*
import kotlinx.android.synthetic.main.item_reviews_adapter.view.*
import kotlinx.android.synthetic.main.item_tips_adapter.view.*


class ReviewsFragment : Fragment() {
    var mRef: DatabaseReference? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_reviews, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View?) {
        mRef = FirebaseDatabase.getInstance().getReference("Feedback")
     view?.reviewsRecyclerView?.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        val firebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<ReviewModel, ViewHolder>(
            ReviewModel::class.java, R.layout.item_reviews_adapter,
            ViewHolder::class.java,mRef){

            override fun populateViewHolder(p0: ViewHolder?, p1: ReviewModel?, p2: Int) {
                p0?.itemView?.txtNameReviews?.text = p1?.username
                p0?.itemView?.txtTilteReview?.text = p1?.userfeedback
                p0?.itemView?.ratingbar?.rating.toString()
            }

        }
        view?.reviewsRecyclerView?.adapter = firebaseRecyclerAdapter

    }


}