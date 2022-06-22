package com.example.devnologyteste

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.devnologyteste.data.CartRepository
import com.example.devnologyteste.databinding.ActivityBottomNavigationBinding
import com.google.android.material.badge.BadgeDrawable

class BottomNavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        navController = findNavController(R.id.nav_host_fragment_activity_bottom_navigation)

        setSupportActionBar(binding.myToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.navigation_detail, R.id.navigation_checkout -> {
                    navView.visibility = View.GONE
                    //supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    binding.myToolbar.visibility = View.GONE
                }
                else -> {
                    navView.visibility = View.VISIBLE
                    setSupportActionBar(binding.myToolbar)
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    binding.myToolbar.visibility = View.VISIBLE
                }
            }
        }

        val badge = navView.getOrCreateBadge(R.id.navigation_cart)
        badge.badgeGravity = BadgeDrawable.BOTTOM_END
        updateBadge()

        navView.setupWithNavController(navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
    fun updateBadge() {
        val badge = binding.navView.getOrCreateBadge(R.id.navigation_cart)
        val number = CartRepository.getInstance().getItemCount()
        if(number > 0) {
            badge.number = number
            badge.isVisible = true
        } else {
            badge.isVisible = false
        }
    }
}