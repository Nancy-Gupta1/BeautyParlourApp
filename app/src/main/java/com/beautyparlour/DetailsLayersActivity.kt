package com.beautyparlour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.beautyparlour.adapter.MakeupSliderAdapter
import kotlinx.android.synthetic.main.activity_detail_v_cut.*
import kotlinx.android.synthetic.main.activity_detail_v_cut.txtVappoint
import kotlinx.android.synthetic.main.activity_details_layers.*
import kotlinx.android.synthetic.main.custom_toolbar.*
import java.util.*

class DetailsLayersActivity : AppCompatActivity() {
    lateinit var viewPager: ViewPager
    var imageId  = intArrayOf(
        R.drawable.ic_layers4,
        R.drawable.ic_layers1,
        R.drawable.ic_layers2,
        R.drawable.ic_layers3,
        R.drawable.ic_layers2,)
    lateinit var sliderDotspanel: LinearLayout
    lateinit var adapter: MakeupSliderAdapter

    var imagesName = arrayOf("Make Up", "Hair style", "Hair Color", "Hair Cut", "Facial")
    var currentPage = 0
    var timer: Timer? = null
    val DELAY_MS: Long = 500 //delay in milliseconds before task is to be executed
    val PERIOD_MS: Long = 3000 // time in milliseconds between successive task executions.
    private var dotscount = 0
    private lateinit var dots: Array<ImageView?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_layers)
        val toolbar: Toolbar = findViewById(R.id.toolbar1)
        setSupportActionBar(toolbar)
        toolbarName.text = "Layers cut"
        initView()
    }

    private fun initView() {
        menu_btn.setOnClickListener {
            onBackPressed()
        }
        txtLayersappoint.setOnClickListener {
            startActivity(Intent(this@DetailsLayersActivity, AppoinmentActivity::class.java))
        }
        sliderDotspanel = findViewById(R.id.detailMakeupviewpagerSliderDotsHome)
        viewPager = findViewById(R.id.detailMakeupviewpager)
        adapter = MakeupSliderAdapter(this@DetailsLayersActivity, imageId , imagesName)
        viewPager.adapter = adapter
        dotscount = adapter.count
        dots = arrayOfNulls(dotscount)
        for (i in 0 until dotscount) {
            dots[i] = ImageView(this)
            dots[i]!!.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
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
                applicationContext,
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
                                applicationContext,
                                R.drawable.non_active_dot
                            )
                        )
                }
                dots[position]!!
                    .setImageDrawable(
                        ContextCompat.getDrawable(
                            applicationContext,
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
}