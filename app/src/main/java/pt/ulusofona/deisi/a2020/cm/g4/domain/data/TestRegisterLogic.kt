package pt.ulusofona.deisi.a2020.cm.g4.domain.data

import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.entities.CovidData
import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.entities.Test
import pt.ulusofona.deisi.a2020.cm.g4.data.repositories.CovidBuddyRepository
import pt.ulusofona.deisi.a2020.cm.g4.ui.listeners.FetchDashboardListener
import pt.ulusofona.deisi.a2020.cm.g4.ui.listeners.FetchRepositoryListener
import pt.ulusofona.deisi.a2020.cm.g4.ui.listeners.FetchRepositoryTestRegisterListener
import pt.ulusofona.deisi.a2020.cm.g4.ui.listeners.FetchTestRegisterListener
import java.io.File

class TestRegisterLogic(private val repository: CovidBuddyRepository) : FetchRepositoryTestRegisterListener {

    fun registerTest(date: String, result: String, local: String, image: String?, dateReg: String) {
        repository.registerTestRegisterListener(this)
        repository.saveTestLocal(date, result, local, image, dateReg)

    }


    private val listeners = mutableListOf<FetchTestRegisterListener>()


    fun registerTestRegisterListener(listener: FetchTestRegisterListener) {

        listeners.add(listener)

    }


    fun unregisterTestRegisterListener(listener: FetchTestRegisterListener) {

        listeners.remove(listener)

    }


    fun notifyTestRegisterListeners(testRegister: Test) {

        listeners.forEach { it.onTestRegisterFetched(testRegister) }

    }




    override fun onRepositoryTestRegisterFetched(testRegister: Test) {
        notifyTestRegisterListeners(testRegister)
    }

}