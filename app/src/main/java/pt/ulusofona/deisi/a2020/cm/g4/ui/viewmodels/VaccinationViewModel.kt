package pt.ulusofona.deisi.a2020.cm.g4.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.CovidBuddyDatabase
import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.entities.VaccinationData
import pt.ulusofona.deisi.a2020.cm.g4.data.repositories.CovidBuddyRepository
import pt.ulusofona.deisi.a2020.cm.g4.domain.data.VaccinationLogic
import pt.ulusofona.deisi.a2020.cm.g4.ui.listeners.FetchVaccinesListener
import pt.ulusofona.deisi.a2020.cm.g4.ui.listeners.ReceiveVaccinesListener
import ulht.cm.acalculator.data.remote.RetrofitBuilder

const val ENDPOINT_VACCINES = "https://www.vacinacaocovid19.pt/api/vaccines/"

class VaccinationViewModel(application: Application) : AndroidViewModel(application), FetchVaccinesListener {

    private val storage = CovidBuddyDatabase.getInstance(application).VaccineDataDAO()
    private val storageDashboard = CovidBuddyDatabase.getInstance(application).CovidDataDAO()
    private val repository = CovidBuddyRepository(storageDashboard, storage, RetrofitBuilder.getInstance(ENDPOINT_VACCINES))

    private val vaccinationLogic = VaccinationLogic(repository)

    fun getVaccines() {
        vaccinationLogic.registerVaccinesListener(this)
        CoroutineScope(Dispatchers.IO).launch {
            vaccinationLogic.getVaccination()
        }

    }


    override fun onVaccinesFetched(vaccination: VaccinationData) {

        CoroutineScope(Dispatchers.Main).launch {
            notifyVaccinesListeners(vaccination)
        }

    }


    private val listeners = mutableListOf<ReceiveVaccinesListener>()


    fun registerVaccinesListener(listener: ReceiveVaccinesListener) {

        listeners.add(listener)

    }


    fun unregisterVaccinesListener(listener: ReceiveVaccinesListener) {

        listeners.remove(listener)

    }


    fun notifyVaccinesListeners(vaccination: VaccinationData) {


        listeners.forEach { it.onReceiveVaccines(vaccination) }


    }


}