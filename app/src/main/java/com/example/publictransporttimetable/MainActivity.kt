package com.example.publictransporttimetable

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.publictransporttimetable.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

//        @Suppress("UNUSED_VARIABLE")
//        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
//            this,
//            R.layout.activity_main
//        )
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.myNavHostFragment)
//        navController = navHostFragment?.findNavController()!!
//
//        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}