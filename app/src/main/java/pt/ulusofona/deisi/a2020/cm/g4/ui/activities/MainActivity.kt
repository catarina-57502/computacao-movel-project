package pt.ulusofona.deisi.a2020.cm.g4.ui.activities

import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import pt.ulusofona.deisi.a2020.cm.g4.ui.utils.NavigationManager
import pt.ulusofona.deisi.a2020.cm.g4.R
import pt.ulusofona.deisi.a2020.cm.g4.data.local.list.DataSource


const val EXTRA_TEST = "pt.ulusofona.deisi.a2020.cm.g4.TEST"

var current_level = 0.25f

var danger_levels = listOf<Float>(0.25f, 0.5f, 0.75f)

var count = 0

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "o método onCreate foi invocado")
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupDrawerMenu()
        if(!screenRotated(savedInstanceState)){
            NavigationManager.goToDashboardFragment(supportFragmentManager)
        }

        val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { ifilter ->
            this.registerReceiver(null, ifilter)
        }
        val batteryPct: Float? = batteryStatus?.let { intent ->
            val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            level * 100 / scale.toFloat()
        }

        if(batteryPct!=null){
            if(batteryPct<20.0){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                delegate.applyDayNight()
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                delegate.applyDayNight()
            }
        }
    }
    private fun screenRotated(savedInstanceState: Bundle?) : Boolean{
        return savedInstanceState != null
    }

    private fun setupDrawerMenu(){
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close)
        nav_drawer.setNavigationItemSelectedListener(this)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val bundle = Bundle()
        bundle.putParcelableArrayList(EXTRA_TEST, DataSource.tests)
        when(item.itemId){
            R.id.nav_dashboard -> NavigationManager.goToDashboardFragment(supportFragmentManager)
            R.id.nav_register -> NavigationManager.goToTestRegisterFragment(supportFragmentManager)
            R.id.nav_list -> NavigationManager.goToTestListFragment(supportFragmentManager, bundle)
            R.id.nav_vaccination -> NavigationManager.goToVaccinationFragment(supportFragmentManager)
            R.id.nav_contacts -> NavigationManager.goToContactsFragment(supportFragmentManager)
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START)
        }else if(supportFragmentManager.backStackEntryCount==1){
            finish()
        }else{
            super.onBackPressed()
        }
    }
}