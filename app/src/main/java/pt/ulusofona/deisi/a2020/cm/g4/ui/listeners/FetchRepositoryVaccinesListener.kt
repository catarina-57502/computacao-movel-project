package pt.ulusofona.deisi.a2020.cm.g4.ui.listeners

import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.entities.VaccinationData

interface FetchRepositoryVaccinesListener {
    fun onRepositoryVaccinesFetched(vaccination: VaccinationData)
}