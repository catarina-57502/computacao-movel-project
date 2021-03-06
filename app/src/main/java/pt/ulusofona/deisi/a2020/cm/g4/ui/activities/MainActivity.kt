package pt.ulusofona.deisi.a2020.cm.g4.ui.activities

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.BatteryManager
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import pt.ulusofona.deisi.a2020.cm.g4.R
import pt.ulusofona.deisi.a2020.cm.g4.data.sensors.battery.Battery
import pt.ulusofona.deisi.a2020.cm.g4.data.sensors.battery.OnBatteryCurrentListener
import pt.ulusofona.deisi.a2020.cm.g4.ui.utils.NavigationManager


const val EXTRA_TEST = "pt.ulusofona.deisi.a2020.cm.g4.TEST"

var current_level = 0.2f

var danger_levels = listOf<Float>(0.2f, 0.4f, 0.6f, 0.8f)

var count = 0

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, OnBatteryCurrentListener {

    private val TAG = MainActivity::class.java.simpleName

    val battery = Battery.start(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "o método onCreate foi invocado")
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupDrawerMenu()
         if(!screenRotated(savedInstanceState)){
            NavigationManager.goToDashboardFragment(supportFragmentManager)
        }

        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        if (!isConnected){
            Toast.makeText(applicationContext, getString(R.string.internet), Toast.LENGTH_SHORT).show()
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
        //val bundle = Bundle()
        when(item.itemId){
            R.id.nav_dashboard -> NavigationManager.goToDashboardFragment(supportFragmentManager)
            R.id.nav_register -> NavigationManager.goToTestRegisterFragment(supportFragmentManager)
            R.id.nav_list -> NavigationManager.goToTestListFragment(supportFragmentManager)
            R.id.nav_vaccination -> NavigationManager.goToVaccinationFragment(supportFragmentManager)
            R.id.nav_contacts -> NavigationManager.goToContactsFragment(supportFragmentManager)
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCurrentChanged(current: Float?) {
        Battery(this).getBatteryCurrentNow()
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