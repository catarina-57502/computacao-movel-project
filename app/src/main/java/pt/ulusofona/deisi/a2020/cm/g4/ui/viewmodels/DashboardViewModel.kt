package pt.ulusofona.deisi.a2020.cm.g4.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.CovidBuddyDatabase
import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.entities.CovidData
import pt.ulusofona.deisi.a2020.cm.g4.data.repositories.CovidBuddyRepository
import pt.ulusofona.deisi.a2020.cm.g4.domain.data.DashboardLogic
import pt.ulusofona.deisi.a2020.cm.g4.ui.listeners.FetchDashboardListener
import pt.ulusofona.deisi.a2020.cm.g4.ui.listeners.ReceiveDashboardListener
import ulht.cm.acalculator.data.remote.RetrofitBuilder

const val ENDPOINT = "https://covid19-api.vost.pt/"

class DashboardViewModel(application: Application) : AndroidViewModel(application), FetchDashboardListener {

    private val storage = CovidBuddyDatabase.getInstance(application).CovidDataDAO()
    private val storageVaccines = CovidBuddyDatabase.getInstance(application).VaccineDataDAO()
    private val repository = CovidBuddyRepository(storage, storageVaccines, null, RetrofitBuilder.getInstance(ENDPOINT))

    private val dashboardLogic = DashboardLogic(repository)

    fun getDashboard() {

        dashboardLogic.registerDashboardListener(this)
        CoroutineScope(Dispatchers.IO).launch {
            dashboardLogic.getDashboard()
        }

    }


    override fun onDashboardFetched(dashboard: CovidData) {

        CoroutineScope(Dispatchers.Main).launch {
            notifyDashboardListeners(dashboard)
        }


    }


    private val listeners = mutableListOf<ReceiveDashboardListener>()


    fun registerDashboardListener(listener: ReceiveDashboardListener) {

        listeners.add(listener)

    }


    fun unregisterDashboardListener(listener: ReceiveDashboardListener) {

        listeners.remove(listener)

    }


    fun notifyDashboardListeners(dashboard: CovidData) {


        listeners.forEach { it.onReceiveDashboard(dashboard) }


    }


}