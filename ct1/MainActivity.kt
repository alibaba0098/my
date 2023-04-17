package com.example.ct1

import android.content.res.Configuration
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.viewPager)
        viewPager.isUserInputEnabled = false // کاربر تنها در صفحه فعال می‌تواند باشد


//        application direction => right to left //
            val configuration: Configuration = resources.configuration
            configuration.setLayoutDirection(Locale("fa"))
            resources.updateConfiguration(configuration, resources.displayMetrics)

        // * //
// custom action bar //
        supportActionBar?.apply {
            val lp = ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER
            )
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            setCustomView(layoutInflater.inflate(R.layout.custom_action_bar, null), lp)
        }
        // * //

//       drawer menu icon display setting         //
        drawerLayout = findViewById(R.id.drawerLayout)
        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        actionBarToggle.drawerArrowDrawable.setDirection(DrawerArrowDrawable.ARROW_DIRECTION_START)
        drawerLayout.addDrawerListener(actionBarToggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        actionBarToggle.syncState()
//        *         //

//        menu item click listener          //
        navView = findViewById(R.id.navigationView)
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.profile -> {
                    Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.basket -> {
                    true
                }
                R.id.setting -> {
                    true
                }
                else -> {
                    false
                }
            }
        }
//        *         //
        bottomNavigationView = findViewById(R.id.bottom_nv)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bm_home -> {
                    viewPager.currentItem = 0
                    bottomNavigationView.menu.findItem(R.id.bm_home)?.isChecked = true
                    true
                }
                R.id.bm_explore -> {
                    viewPager.currentItem = 1
                    bottomNavigationView.menu.findItem(R.id.bm_explore)?.isChecked = true
                    true
                }
                R.id.bm_setting -> {
                    viewPager.currentItem = 2
                    bottomNavigationView.menu.findItem(R.id.bm_setting)?.isChecked = true
                    true
                }
                R.id.bm_food -> {
                    viewPager.currentItem = 3
                    bottomNavigationView.menu.findItem(R.id.bm_food)?.isChecked = true
                    true
                }
                R.id.bm_travel -> {
                    viewPager.currentItem = 4
                    bottomNavigationView.menu.findItem(R.id.bm_travel)?.isChecked = true
                    true
                }
                else -> {
                    false
                }
            }
        }
        tabAdapter()
    }

    private fun tabAdapter() {
        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter
    }

    //    menu icon click action    //
    override fun onSupportNavigateUp(): Boolean {
        if (drawerLayout.isDrawerOpen(navView)) {
            drawerLayout.closeDrawer(navView)
        } else {
            drawerLayout.openDrawer(navView)
        }
        return true
    }
//    *     //

//    back button press action      //
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("آیا می‌خواهید از برنامه خارج شوید؟")
                .setCancelable(true)
                .setPositiveButton("بله") { _, _ -> finishAffinity() }
                .setNegativeButton("خیر") { dialog, _ -> dialog.cancel() }
            val alert = builder.create()
            alert.show()
        }
    }
//    *     //
}