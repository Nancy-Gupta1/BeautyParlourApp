package com.beautyparlour.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.beautyparlour.R
import com.google.firebase.database.core.Context

class HomeSliderAdapter(
    private val activity: Activity,
    private val imagesArray: IntArray,
    private val namesArray: Array<String>
) : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = activity.layoutInflater
        //creating  xml file for custom viewpager
        val viewItem = inflater.inflate(R.layout.home_slider, container, false)
        //finding id
        val imageView = viewItem.findViewById<ImageView>(R.id.imageViewHome)
        val title = viewItem.findViewById<TextView>(R.id.txttitleHome)
        //setting data
        title.text = namesArray[position]
        imageView.setBackgroundResource(imagesArray[position])
        container.addView(viewItem)
        return viewItem
    }

    override fun getCount(): Int {
        // TODO Auto-generated method stub
        return imagesArray.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        // TODO Auto-generated method stub
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        // TODO Auto-generated method stub
        container.removeView(`object` as View)
    }
}