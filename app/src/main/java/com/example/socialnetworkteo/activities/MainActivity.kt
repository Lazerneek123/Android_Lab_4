package com.example.socialnetworkteo.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.socialnetworkteo.R
import com.example.socialnetworkteo.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    companion object {
        @JvmStatic
        lateinit var EXTRA_MESSAGE: String
    }

    init {
        EXTRA_MESSAGE = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_friends, R.id.update
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    fun openFriendMainActivity(friendId: Int) {
        val intent = Intent(this, UserActivity::class.java)
        intent.putExtra(EXTRA_MESSAGE, friendId)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun listenerInformation(item: MenuItem) {
        Toast.makeText(this, R.string.information_app, Toast.LENGTH_LONG).show()
    }

    fun changeTheme(view: View) {
        //val c : Int = ContextCompat.getColor(this, R.color.light)
        //recreate()
        //view.setBackgroundResource(R.drawable.icon_my)
        //findViewById<LinearLayout>(R.id.linearLayoutUserDown).setBackgroundColor(Color.rgb(11, 7, 17))
    }

    fun openAddUserActivity(view: View) {
        val intent = Intent(this, AddUserActivity::class.java)
        startActivity(intent)
    }
}