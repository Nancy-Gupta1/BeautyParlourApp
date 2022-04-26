package com.beautyparlour

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.beautyparlour.fragment.*
import com.beautyparlour.utils.BaseActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_navigation_bottom.*
import com.beautyparlour.utils.PreferenceHelper.set
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.custom_dialog.*

class NavigationActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbarTitle.text = "Home"

        val drawerToggle = ActionBarDrawerToggle(this,drawer_layout,R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        drawerToggle.isDrawerIndicatorEnabled = false
        drawerToggle.setHomeAsUpIndicator(R.drawable.ic_menulist1)

        nav_view.setNavigationItemSelectedListener(this)

        navbottam_view.setOnNavigationItemSelectedListener(Bottam)
        navbottam_view.itemIconTintList = null


        replceFragment(HomeFragment())
    }

    private fun replceFragment(fragment:Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frmeLayout,fragment).commit()
    }

    val Bottam=BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId)
        {
            R.id.nav_home->
            {
                replceFragment(HomeFragment())
                toolbarTitle.text = "Home"
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_services->
            {
                replceFragment(SeviceFragment())
                toolbarTitle.text = "Services"
                return@OnNavigationItemSelectedListener true
            }

            R.id.nav_protfolio->
            {
                replceFragment(ProtfolioFragment())
                toolbarTitle.text = "Protfolio"
                return@OnNavigationItemSelectedListener true
            }

            R.id.nav_tips->
            {
                replceFragment(TipsFragment())
                toolbarTitle.text = "Tips"
                return@OnNavigationItemSelectedListener true
            }


        }
        false
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                replceFragment(HomeFragment())
            }

            R.id.nav_about -> {
                toolbarTitle.text = "About"
                replceFragment(AboutFragment())
            }

            R.id.nav_expert -> {
                toolbarTitle.text = "Expert"
                replceFragment(ExpertFragment())
            }

            R.id.nav_reviews -> {
                toolbarTitle.text = "Reviews"
                replceFragment(ReviewsFragment())
            }

            R.id.nav_feedback -> {
                toolbarTitle.text = "Feedback"
                replceFragment(FeedbackFragment())
            }

            R.id.nav_logout -> {
                //replceFragment(LogoutFragment())
                val builder = AlertDialog.Builder(this)
                val inflater = this.layoutInflater

                val dialogView = inflater.inflate(R.layout.custom_dialog, null)
                builder.setView(dialogView)

                // builder.setMessage("Are you sure you want to log out? " )

                val buttonLogout = dialogView.findViewById<TextView>(R.id.txtLogout)
                val buttonCancle = dialogView.findViewById<TextView>(R.id.txtCancle)
                val alertDialog: AlertDialog = builder.create()
                buttonLogout.setOnClickListener {
                    alertDialog.dismiss()
                    prifre["IS_LOGIN"] = false
                    startActivity(Intent(this, LoginActivity::class.java))

                }

                buttonCancle.setOnClickListener {
                    alertDialog.dismiss()
//                    Toast.makeText(this, "clicked No", Toast.LENGTH_LONG).show()
//                    startActivity(Intent(this, NavigationActivity::class.java))

                }
                // Create the AlertDialog
               // val alertDialog: AlertDialog = builder.create()
                //alertDialog.window!!.setBackgroundDrawableResource(R.color.dark_color)
                // Set other dialog properties
//                alertDialog.setCancelable(false)
               alertDialog.show()
            }

        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawer_layout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}