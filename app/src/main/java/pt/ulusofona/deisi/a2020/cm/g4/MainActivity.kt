package pt.ulusofona.deisi.a2020.cm.g4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

const val EXTRA_TEST = "pt.ulusofona.deisi.a2020.cm.g4.TEST"

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "o método onCreate foi invocado")
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupDrawerMenu()
        if(!screenRotated(savedInstanceState)){
            NavigationManager.goToTestRegisterFragment(supportFragmentManager, null)
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
            R.id.nav_register -> NavigationManager.goToTestRegisterFragment(supportFragmentManager, null)
            R.id.nav_list -> NavigationManager.goToTestListFragment(supportFragmentManager, bundle)
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