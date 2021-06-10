package pt.ulusofona.deisi.a2020.cm.g4.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.CovidBuddyDatabase
import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.entities.Test
import pt.ulusofona.deisi.a2020.cm.g4.data.repositories.CovidBuddyRepository
import pt.ulusofona.deisi.a2020.cm.g4.domain.data.TestListLogic
import pt.ulusofona.deisi.a2020.cm.g4.domain.data.TestRegisterLogic
import pt.ulusofona.deisi.a2020.cm.g4.ui.listeners.FetchTestListListener
import pt.ulusofona.deisi.a2020.cm.g4.ui.listeners.FetchTestRegisterListener
import pt.ulusofona.deisi.a2020.cm.g4.ui.listeners.ReceiveTestListListener
import pt.ulusofona.deisi.a2020.cm.g4.ui.listeners.ReceiveTestRegisterListener

class TestListViewModel(application: Application) : AndroidViewModel(application), FetchTestListListener {

    private val storage = CovidBuddyDatabase.getInstance(application).TestDAO()
    private val storageVaccines = CovidBuddyDatabase.getInstance(application).VaccineDataDAO()
    private val storageDashboard = CovidBuddyDatabase.getInstance(application).CovidDataDAO()

    private val repository = CovidBuddyRepository(storageDashboard, storageVaccines, storage, null)

    private val testListLogic = TestListLogic(repository)


    fun getTestsList(){
        testListLogic.registerTestListListener(this)
        CoroutineScope(Dispatchers.IO).launch {
            testListLogic.getAllTests()
        }
    }


    override fun onTestListFetched(tests: List<Test>) {

        CoroutineScope(Dispatchers.Main).launch {
            notifyTestListListeners(tests)
        }


    }


    private val listeners = mutableListOf<ReceiveTestListListener>()


    fun registerTestListListener(listener: ReceiveTestListListener) {

        listeners.add(listener)

    }


    fun unregisterTestListListener(listener: ReceiveTestListListener) {

        listeners.remove(listener)

    }


    fun notifyTestListListeners(tests: List<Test>) {


        listeners.forEach { it.onReceiveTestList(tests) }


    }

}