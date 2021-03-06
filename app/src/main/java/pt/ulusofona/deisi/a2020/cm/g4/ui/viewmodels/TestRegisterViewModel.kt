package pt.ulusofona.deisi.a2020.cm.g4.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.CovidBuddyDatabase
import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.entities.Test
import pt.ulusofona.deisi.a2020.cm.g4.data.repositories.CovidBuddyRepository
import pt.ulusofona.deisi.a2020.cm.g4.domain.data.TestRegisterLogic
import pt.ulusofona.deisi.a2020.cm.g4.ui.listeners.*

class TestRegisterViewModel(application: Application) : AndroidViewModel(application), FetchTestRegisterListener {

    private val storage = CovidBuddyDatabase.getInstance(application).TestDAO()
    private val storageVaccines = CovidBuddyDatabase.getInstance(application).VaccineDataDAO()
    private val storageDashboard = CovidBuddyDatabase.getInstance(application).CovidDataDAO()

    private val repository = CovidBuddyRepository(storageDashboard, storageVaccines, storage, null)

    private val testRegisterLogic = TestRegisterLogic(repository)


    fun onClickSubmit(date: String, result: String, local: String, image: String?, dateReg: String){
        testRegisterLogic.registerTestRegisterListener(this)
        CoroutineScope(Dispatchers.IO).launch {
            testRegisterLogic.registerTest(date, result, local, image, dateReg)
        }
    }

    override fun onTestRegisterFetched(testRegister: Test) {

        CoroutineScope(Dispatchers.Main).launch {
            notifyTestRegisterListeners(testRegister)
        }


    }


    private val listeners = mutableListOf<ReceiveTestRegisterListener>()


    fun registerTestRegisterListener(listener: ReceiveTestRegisterListener) {

        listeners.add(listener)

    }


    fun unregisterTestRegisterListener(listener: ReceiveTestRegisterListener) {

        listeners.remove(listener)

    }


    fun notifyTestRegisterListeners(testRegister: Test) {


        listeners.forEach { it.onReceiveTestRegister(testRegister) }


    }


}