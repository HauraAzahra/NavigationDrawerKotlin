package com.haura.idn.navigationdrawerkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {

    //mendeklarasikan variable untuk Toolbar,DrawerLayout,NavigationView nya
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    lateinit var profileFragment: ProfileFragment
    lateinit var messageFragment: MessageFragment
    lateinit var friendsFragment: FriendsFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //inisialisasi kan
        toolbar = findViewById(R.id.toolbar)
        //toolbar di dukung dengan
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            //di isi dengan 5 argumen = context activity ini,drawerLayout,toolbar,openDrawer,closeDrawer
            //di kasih 0,karena kita ga butuh mereka
            this, drawerLayout, toolbar, R.string.open_msg, R.string.close_msg
        )

        //make default fragment when open apps
        profileFragment = ProfileFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout,profileFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        //tampilin toogle ke drawerlayout
        drawerLayout.addDrawerListener(toggle)
        //sinkronkan toogle
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        //kasih conditional
        when (item.itemId) {
            R.id.nav_profile -> {
                // panggil object dari profile fragment,mau pindah ke mana pas di klik? ke Profile Fragment
                profileFragment = ProfileFragment()
                //memulai transaksi
                supportFragmentManager.beginTransaction()
                        //replace = mengganti frame layout nya menjadi fragment yang sesuai
                    .replace(R.id.frame_layout,profileFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        //commit = untuk menjalankan transaksi nya
                    .commit()
            }

            R.id.nav_messages -> {
                messageFragment = MessageFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout,messageFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }

            R.id.nav_friends -> {
                friendsFragment = FriendsFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout,friendsFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }

            R.id.nav_update -> {
                Toast.makeText(this, "Update clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Sign out clicked", Toast.LENGTH_SHORT).show()
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
