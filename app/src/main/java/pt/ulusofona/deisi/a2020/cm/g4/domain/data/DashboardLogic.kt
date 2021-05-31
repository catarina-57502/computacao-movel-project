package pt.ulusofona.deisi.a2020.cm.g4.domain.data

import net.objecthunter.exp4j.ExpressionBuilder
import pt.ulusofona.deisi.a2020.cm.g4.data.local.list.DataSource
import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.entities.CovidData
import pt.ulusofona.deisi.a2020.cm.g4.data.repositories.CovidBuddyRepository
import pt.ulusofona.deisi.a2020.cm.g4.ui.listeners.FetchDashboardListener
import pt.ulusofona.deisi.a2020.cm.g4.ui.listeners.FetchRepositoryListener
import pt.ulusofona.deisi.a2020.cm.g4.ui.listeners.ReceiveDashboardListener

class DashboardLogic(private val repository: CovidBuddyRepository) : FetchRepositoryListener {

    fun getDashboard() {
        repository.registerDashboardListener(this)
        repository.getCovidData()

    }


        private val listeners = mutableListOf<FetchDashboardListener>()


        fun registerDashboardListener(listener: FetchDashboardListener) {

            listeners.add(listener)

        }


        fun unregisterDashboardListener(listener: FetchDashboardListener) {

            listeners.remove(listener)

        }


        fun notifyDashboardListeners(dashboard: CovidData) {

            listeners.forEach { it.onDashboardFetched(dashboard) }

        }




    override fun onRepositoryFetched(dashboard: CovidData) {
        notifyDashboardListeners(dashboard)
    }

}