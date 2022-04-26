package com.beautyparlour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.beautyparlour.utils.BaseActivity
import com.beautyparlour.utils.PreferenceHelper.get

class MainActivity : BaseActivity() {
    private val SPLASH_TIME_OUT:Long = 3000
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handler = Handler(Looper.myLooper()!!)

       /* Handler().postDelayed({

        },SPLASH_TIME_OUT.toLong())*/
        handler.postDelayed({
            if (prifre["IS_LOGIN"]!!){
                startActivity(Intent(this,NavigationActivity::class.java))
                finish()
            }else{
                startActivity(Intent(this,ScreenShowActivity::class.java))
                finish()
            }

//            startActivity(Intent(this, ScreenShowActivity::class.java))
//                finish()

        }, SPLASH_TIME_OUT)
    }
}