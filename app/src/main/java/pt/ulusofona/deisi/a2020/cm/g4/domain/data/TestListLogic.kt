package pt.ulusofona.deisi.a2020.cm.g4.domain.data

import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.entities.Test
import pt.ulusofona.deisi.a2020.cm.g4.data.repositories.CovidBuddyRepository
import pt.ulusofona.deisi.a2020.cm.g4.ui.listeners.FetchRepositoryTestListListener
import pt.ulusofona.deisi.a2020.cm.g4.ui.listeners.FetchRepositoryTestRegisterListener
import pt.ulusofona.deisi.a2020.cm.g4.ui.listeners.FetchTestListListener
import pt.ulusofona.deisi.a2020.cm.g4.ui.listeners.FetchTestRegisterListener

class TestListLogic(private val repository: CovidBuddyRepository) : FetchRepositoryTestListListener {

    suspend fun getAllTests() {
        repository.registerTestListListener(this)
        repository.getListTests()

    }


    private val listeners = mutableListOf<FetchTestListListener>()


    fun registerTestListListener(listener: FetchTestListListener) {

        listeners.add(listener)

    }


    fun unregisterTestListListener(listener: FetchTestListListener) {

        listeners.remove(listener)

    }


    fun notifyTestListListeners(tests: List<Test>) {

        listeners.forEach { it.onTestListFetched(tests) }

    }




    override fun onRepositoryTestListFetched(tests: List<Test>) {
        notifyTestListListeners(tests)
    }
}