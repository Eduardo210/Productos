package com.sahuayo.productos

import android.content.ContentValues.TAG
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.sahuayo.productos.SQlite.DataBaseManager
import com.sahuayo.productos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentHost) as NavHostFragment


        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(
            navController
        )

        val appBarConfig = AppBarConfiguration(
            setOf(
                R.id.registerFragment,
                R.id.updateFragment,
                R.id.deleteFragment
            )
        )

        setupActionBarWithNavController(navController, appBarConfig)

    }
}