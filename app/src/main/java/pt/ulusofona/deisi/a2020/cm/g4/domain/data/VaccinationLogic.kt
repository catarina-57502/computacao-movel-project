package pt.ulusofona.deisi.a2020.cm.g4.domain.data

import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.entities.VaccinationData
import pt.ulusofona.deisi.a2020.cm.g4.data.repositories.CovidBuddyRepository
import pt.ulusofona.deisi.a2020.cm.g4.ui.listeners.FetchRepositoryVaccinesListener
import pt.ulusofona.deisi.a2020.cm.g4.ui.listeners.FetchVaccinesListener

class VaccinationLogic(private val repository: CovidBuddyRepository) : FetchRepositoryVaccinesListener {

    fun getVaccination() {
        repository.registerVaccinesListener(this)
        repository.getVaccinesData()
    }


    private val listeners = mutableListOf<FetchVaccinesListener>()


    fun registerVaccinesListener(listener: FetchVaccinesListener) {

        listeners.add(listener)

    }


    fun unregisterVaccinesListener(listener: FetchVaccinesListener) {

        listeners.remove(listener)

    }


    fun notifyVaccinesListeners(vaccination: VaccinationData) {

        listeners.forEach { it.onVaccinesFetched(vaccination) }

    }




    override fun onRepositoryVaccinesFetched(vaccination: VaccinationData) {
        notifyVaccinesListeners(vaccination)
    }

}