package pt.ulusofona.deisi.a2020.cm.g4.ui.listeners

import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.entities.CovidData
import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.entities.Test

interface FetchRepositoryTestListListener {
    fun onRepositoryTestListFetched(tests: List<Test>)
}