package com.example.lelangapps

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.lelangapps.databinding.ActivityNavigationBinding
import com.example.lelangapps.ui.login.LoginActivity
import com.example.lelangapps.util.Prefs

class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_navigation)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_dashboard, R.id.navigation_auction, R.id.navigation_transaction, R.id.navigation_profil
            )
        )
       // setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setOnItemSelectedListener {

            if(it.itemId == R.id.navigation_profil){
            if (Prefs.isLogin){
                Log.d("TAG","sudah login")
                navController.navigate(it.itemId)
            }else {
                startActivity(Intent(this,LoginActivity :: class.java))
                Log.d("TAG","belum login")
            }
            }else {
                navController.navigate(it.itemId)
                Log.d("TAG","onCreate yg lain" + it.itemId)
            }

            return@setOnItemSelectedListener true
        }
    }
}