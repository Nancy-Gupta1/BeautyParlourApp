package com.beautyparlour.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.beautyparlour.*
import com.beautyparlour.adapter.CustomAdapter
import com.beautyparlour.adapter.HomeSliderAdapter
import com.beautyparlour.models.HairSpecialistModel
import com.beautyparlour.models.HomeModel
import com.beautyparlour.models.OffersModel
import com.beautyparlour.models.OurBrandsModel
import com.beautyparlour.utils.ViewHolder
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.item_adapter_linearview.view.*
import kotlinx.android.synthetic.main.item_adapter_linearview.view.txtLinearName
import kotlinx.android.synthetic.main.item_adapter_offers.view.*
import kotlinx.android.synthetic.main.item_adater_glide.view.*
import kotlinx.android.synthetic.main.item_adater_glide.view.txtName
import kotlinx.android.synthetic.main.item_ourbrands_adapter.view.*
import java.util.*

class HomeFragment : Fragment() {
    var mRef: DatabaseReference? = null
    lateinit var viewPager: ViewPager
    lateinit var sliderDotspanel: LinearLayout
    lateinit var adapter: HomeSliderAdapter
    var progressStatus = 0


    var image  = intArrayOf(
        R.drawable.ic_splash3,
        R.drawable.ic_splash5,
        R.drawable.ic_splash4,
        R.drawable.ic_splash2,
        R.drawable.ic_splash1,
    )
    var imagesName = arrayOf("Make Up", "Hair style", "Hair Color", "Hair Cut", "Facial")
    var currentPage = 0
    var timer: Timer? = null
    val DELAY_MS: Long = 500 //delay in milliseconds before task is to be executed
    val PERIOD_MS: Long = 3000 // time in milliseconds between successive task executions.
    private var dotscount = 0
    private lateinit var dots: Array<ImageView?>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        SliderView(view)
        initView(view)
        linearView(view)
        allBrandName(view)
        offers(view)
            return  view
    }



    private fun SliderView(view: View?) {
        sliderDotspanel = view?.findViewById(R.id.SliderDotsHome) as LinearLayout
        viewPager = view.findViewById(R.id.viewpagerhome) as ViewPager
        adapter = HomeSliderAdapter(requireActivity(),image,imagesName)
        viewPager.adapter = adapter
        dotscount = adapter.count
        dots = arrayOfNulls(dotscount)
        for (i in 0 until dotscount) {
            dots[i] = ImageView(context)
            dots[i]!!.setImageDrawable(
                ContextCompat.getDrawable(
                    view.context,
                    R.drawable.non_active_dot
                )
            )
            val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            sliderDotspanel.addView(dots[i], params)
        }
        dots[0]!!.setImageDrawable(
            ContextCompat.getDrawable(
                view.context,
                R.drawable.active_dot
            )
        )
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                for (i in 0 until dotscount) {
                    dots[i]!!
                        .setImageDrawable(
                            ContextCompat.getDrawable(
                                view.context,
                                R.drawable.non_active_dot
                            )
                        )
                }
                dots[position]!!
                    .setImageDrawable(
                        ContextCompat.getDrawable(
                            view.context,
                            R.drawable.active_dot
                        )
                    )
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        /*After setting the adapter use the timer */
        val handler = Handler()
        val Update = Runnable {
            if (currentPage == 7) {
                currentPage = 0
            }
            viewPager.setCurrentItem(currentPage++, true)
        }
        timer = Timer() // This will create a new Thread
        timer!!.schedule(object : TimerTask() {
            // task to be scheduled
            override fun run() {
                handler.post(Update)
            }
        }, DELAY_MS, PERIOD_MS)

    }

    private fun offers(view: View?) {
        mRef = FirebaseDatabase.getInstance().getReference("offers")
        view?.recyclerviewOffers?.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
        val firebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<OffersModel,ViewHolder>(
            OffersModel::class.java, R.layout.item_adapter_offers,ViewHolder::class.java,mRef){
            override fun populateViewHolder(p0: ViewHolder?, p1: OffersModel?, p2: Int) {
                Glide.with(activity!!).load(p1?.url).into(p0?.itemView!!.offerImage)

            }

        }
        view?.recyclerviewOffers?.adapter = firebaseRecyclerAdapter

    }

    private fun allBrandName(view: View?) {
        mRef = FirebaseDatabase.getInstance().getReference("ourbrands")
        view?.BrandsrecyclerView?.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        val firebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<OurBrandsModel,ViewHolder>(OurBrandsModel::class.java, R.layout.item_ourbrands_adapter,ViewHolder::class.java,mRef){
            override fun populateViewHolder(p0: ViewHolder?, p1: OurBrandsModel?, p2: Int) {
                Glide.with(activity!!).load(p1?.url).into(p0?.itemView!!.brandImage)
                p0.itemView.brandsName?.text = p1?.name
            }

        }

        view?.BrandsrecyclerView?.adapter = firebaseRecyclerAdapter

    }

    private fun linearView(view:  View?) {
        mRef = FirebaseDatabase.getInstance().getReference("hairspecialist")
        view?.linearRecyclerView?.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        val firebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<HairSpecialistModel,ViewHolder>(HairSpecialistModel::class.java, R.layout.item_adapter_linearview,ViewHolder::class.java,mRef){
            override fun populateViewHolder(p0: ViewHolder?, p1: HairSpecialistModel?, p2: Int) {
                Glide.with(activity!!).load(p1?.image).into(p0?.itemView!!.imglinear)
                p0.itemView.txtLinearName.text = p1?.name
            }

        }
        view?.linearRecyclerView?.adapter = firebaseRecyclerAdapter

    }

    private fun initView(view: View?) {
        mRef = FirebaseDatabase.getInstance().getReference("recyclerview")
        view?.glidRecyclerView?.layoutManager = GridLayoutManager(context,4)
        val firebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<HomeModel,ViewHolder>(HomeModel::class.java, R.layout.item_adater_glide,ViewHolder::class.java,mRef){
            override fun populateViewHolder(p0: ViewHolder?, p1: HomeModel?, p2: Int) {
                p0?.itemView?.imageView?.setOnClickListener {
                    selecteUser(p1?.name, "")

                }
               Glide.with(activity!!).load(p1?.images).into(p0?.itemView!!.imageView)
                p0.itemView.txtName.text = p1?.name
            }

        }
      view?.glidRecyclerView?.adapter = firebaseRecyclerAdapter

    }

    private fun selecteUser(name: String?, s: String) {

        val makeup = "Make Up"
        val cut = "Hair Cut"
        val style = "Hair Style"
        val spa = "Hair Spa"
        val Facial = "Facial"
        val color = "Hair Color"
        val nailart = "Nail Art"
        val more = "More"
        if(makeup.contentEquals(name.toString())){
            startActivity(Intent(context, MakeUpActivity:: class.java))
        }
        else if (cut.contentEquals(name.toString())){
            startActivity(Intent(context, HairCutActivity:: class.java))

        }
        else if (style.contentEquals(name.toString())){
            startActivity(Intent(context, HairStyleActivity:: class.java))

        }
        else if (spa.contentEquals(name.toString())){
            startActivity(Intent(context, HairSpaActivity:: class.java))

        } else if(Facial.contentEquals(name.toString())){
            startActivity(Intent(context, HairFacialActivity:: class.java))
        }
        else if(color.contentEquals(name.toString())){
            startActivity(Intent(context, HairColorActivity:: class.java))
        }
        else if(nailart.contentEquals(name.toString())){
            startActivity(Intent(context, NailArtActivity:: class.java))
        }
        else if(more.contentEquals(name.toString())){
            startActivity(Intent(context, MoreActivity:: class.java))
        }
    }
    //How to make an e-commerce android app?(Part-49)| Banner slider + Firebase | Hindi Tutorial 2019
 //https://www.youtube.com/watch?v=gL10Yi99Zt8
    //slider link
    //https://www.youtube.com/watch?v=HdaUJpT9ehQ



}