package pt.ulusofona.deisi.a2020.cm.g4.data.repositories


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.dao.CovidDataDAO
import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.dao.VaccineDataDAO
import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.entities.CovidData
import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.entities.VaccinationData
import pt.ulusofona.deisi.a2020.cm.g4.data.remote.services.VaccinesService
import pt.ulusofona.deisi.a2020.cm.g4.ui.listeners.FetchRepositoryListener
import pt.ulusofona.deisi.a2020.cm.g4.ui.listeners.FetchRepositoryVaccinesListener
import retrofit2.Retrofit
import ulht.cm.acalculator.data.remote.services.DashboardService
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class CovidBuddyRepository(private val local: CovidDataDAO, private val localVaccines: VaccineDataDAO, private val remote: Retrofit) {

    fun getCovidData(){

        val service = remote.create(DashboardService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val sdf = SimpleDateFormat("dd-MM-yyyy")
            val currentDate = sdf.format(Date())
            var dateToday: CovidData? = local.getByDate(currentDate)

            if(dateToday == null){
                val response = service.getLastUpdate()
                if(response.isSuccessful){
                    if(response.body()?.data == currentDate){
                        val covidToday = CovidData(getPreviousDate(0))

                        val confirmadosTemp = response.body()?.confirmados
                        if(confirmadosTemp != null){
                            covidToday.confirmados = confirmadosTemp
                        }

                        val recuperadosTemp = response.body()?.recuperados
                        if(recuperadosTemp != null){
                            covidToday.recuperados = recuperadosTemp
                        }

                        val obitosTemp = response.body()?.obitos
                        if(obitosTemp != null){
                            covidToday.obito = obitosTemp
                        }

                        val internadosTemp = response.body()?.internados
                        if(internadosTemp != null){
                            covidToday.internados = internadosTemp
                        }

                        val internadosUciTemp = response.body()?.internados_uci
                        if(internadosUciTemp != null){
                            covidToday.internados_uci = internadosUciTemp
                        }

                        val norteTemp = response.body()?.confirmados_arsnorte
                        if(norteTemp != null){
                            covidToday.norte = norteTemp
                        }

                        val centroTemp = response.body()?.confirmados_arscentro
                        if(centroTemp != null){
                            covidToday.centro = centroTemp
                        }

                        val lvtTemp = response.body()?.confirmados_arslvt
                        if(lvtTemp != null){
                            covidToday.lvt = lvtTemp
                        }

                        val alentejoTemp = response.body()?.confirmados_arsalentejo
                        if(alentejoTemp != null){
                            covidToday.alentejo = alentejoTemp
                        }

                        val algarveTemp = response.body()?.confirmados_arsalgarve
                        if(algarveTemp != null){
                            covidToday.algarve = algarveTemp
                        }

                        val madeiraTemp = response.body()?.confirmados_madeira
                        if(madeiraTemp != null){
                            covidToday.madeira = madeiraTemp
                        }

                        val acoresTemp = response.body()?.confirmados_acores
                        if(acoresTemp != null){
                            covidToday.acores = acoresTemp
                        }

                        val rtTemp = response.body()?.rt_nacional
                        if(rtTemp != null){
                            covidToday.rt = rtTemp
                        }

                        var localYesterday = local.getByDate(getPreviousDate(1))

                        if(localYesterday == null){
                            val responseYesterday = service.getEntryByDate(getPreviousDate(1))
                            if(responseYesterday.isSuccessful) {
                                val key = getDaysDifference(getPreviousDate(1)).toString()
                                val covidYesterday = CovidData(getPreviousDate(1))
                                val confirmadosTemp =
                                    responseYesterday.body()?.confirmados?.get(key)

                                if (confirmadosTemp != null) {
                                    covidYesterday.confirmados = confirmadosTemp
                                }
                                val recuperadosTemp =
                                    responseYesterday.body()?.recuperados?.get(key)
                                if (recuperadosTemp != null) {
                                    covidYesterday.recuperados = recuperadosTemp
                                }
                                val obtiosTemp = responseYesterday.body()?.obitos?.get(key)
                                if (obtiosTemp != null) {
                                    covidYesterday.obito = obtiosTemp
                                }
                                val internadosTemp = responseYesterday.body()?.internados?.get(key)
                                if (internadosTemp != null) {
                                    covidYesterday.internados = internadosTemp
                                }
                                val internadosUciTemp =
                                    responseYesterday.body()?.internados_uci?.get(key)
                                if (internadosUciTemp != null) {
                                    covidYesterday.internados_uci = internadosUciTemp
                                }
                                val norteTemp =
                                    responseYesterday.body()?.confirmados_arsnorte?.get(key)
                                if (norteTemp != null) {
                                    covidYesterday.norte = norteTemp
                                }
                                val centroTemp =
                                    responseYesterday.body()?.confirmados_arscentro?.get(key)
                                if (centroTemp != null) {
                                    covidYesterday.centro = centroTemp
                                }
                                val lvtTemp = responseYesterday.body()?.confirmados_arslvt?.get(key)
                                if (lvtTemp != null) {
                                    covidYesterday.lvt = lvtTemp
                                }
                                val alentejoTemp =
                                    responseYesterday.body()?.confirmados_arsalentejo?.get(key)
                                if (alentejoTemp != null) {
                                    covidYesterday.alentejo = alentejoTemp
                                }
                                val algarveTemp =
                                    responseYesterday.body()?.confirmados_arsalgarve?.get(key)
                                if (algarveTemp != null) {
                                    covidYesterday.algarve = algarveTemp
                                }
                                val acoresTemp =
                                    responseYesterday.body()?.confirmados_acores?.get(key)
                                if (acoresTemp != null) {
                                    covidYesterday.acores = acoresTemp
                                }
                                val madeiraTemp =
                                    responseYesterday.body()?.confirmados_madeira?.get(key)
                                if (madeiraTemp != null) {
                                    covidYesterday.madeira = madeiraTemp
                                }
                                val rtTemp = responseYesterday.body()?.rt_nacional?.get(key)
                                if (rtTemp != null) {
                                    covidYesterday.rt = rtTemp
                                }
                                local.insert(covidYesterday)
                            }

                        }

                        var covidYesterday = local.getByDate(getPreviousDate(1))

                        if(covidYesterday!=null){
                            covidToday.confirmados_novos = covidToday.confirmados - covidYesterday.confirmados
                            covidToday.recuperados_novos = covidToday.recuperados - covidYesterday.recuperados
                            covidToday.obitos_novos = covidToday.obito - covidYesterday.obito
                            covidToday.internados_novos = covidToday.internados - covidYesterday.internados
                            covidToday.internados_uci_novos = covidToday.internados_uci - covidYesterday.internados_uci
                            local?.insert(covidToday)
                            notifyDashboardListeners(covidToday)
                        }


                    }else{
                        var localYesterday = local.getByDate(getPreviousDate(1))
                        if(localYesterday==null){
                           val responseYesterday = service.getEntryByDate(getPreviousDate(1))
                            if(responseYesterday.isSuccessful){
                                val key = getDaysDifference(getPreviousDate(1)).toString()
                                val covidYesterday = CovidData(getPreviousDate(1))
                                val confirmadosTemp = responseYesterday.body()?.confirmados?.get(key)

                                if (confirmadosTemp != null) {
                                    covidYesterday.confirmados = confirmadosTemp
                                }
                                val recuperadosTemp = responseYesterday.body()?.recuperados?.get(key)
                                if (recuperadosTemp != null) {
                                    covidYesterday.recuperados = recuperadosTemp
                                }
                                val obtiosTemp = responseYesterday.body()?.obitos?.get(key)
                                if (obtiosTemp != null) {
                                    covidYesterday.obito = obtiosTemp
                                }
                                val internadosTemp = responseYesterday.body()?.internados?.get(key)
                                if (internadosTemp != null) {
                                    covidYesterday.internados = internadosTemp
                                }
                                val internadosUciTemp = responseYesterday.body()?.internados_uci?.get(key)
                                if (internadosUciTemp != null) {
                                    covidYesterday.internados_uci = internadosUciTemp
                                }
                                val norteTemp = responseYesterday.body()?.confirmados_arsnorte?.get(key)
                                if (norteTemp != null) {
                                    covidYesterday.norte = norteTemp
                                }
                                val centroTemp = responseYesterday.body()?.confirmados_arscentro?.get(key)
                                if (centroTemp != null) {
                                    covidYesterday.centro = centroTemp
                                }
                                val lvtTemp = responseYesterday.body()?.confirmados_arslvt?.get(key)
                                if (lvtTemp != null) {
                                    covidYesterday.lvt = lvtTemp
                                }
                                val alentejoTemp = responseYesterday.body()?.confirmados_arsalentejo?.get(key)
                                if (alentejoTemp != null) {
                                    covidYesterday.alentejo = alentejoTemp
                                }
                                val algarveTemp = responseYesterday.body()?.confirmados_arsalgarve?.get(key)
                                if (algarveTemp != null) {
                                    covidYesterday.algarve = algarveTemp
                                }
                                val acoresTemp = responseYesterday.body()?.confirmados_acores?.get(key)
                                if (acoresTemp != null) {
                                    covidYesterday.acores = acoresTemp
                                }
                                val madeiraTemp = responseYesterday.body()?.confirmados_madeira?.get(key)
                                if (madeiraTemp != null) {
                                    covidYesterday.madeira = madeiraTemp
                                }
                                val rtTemp = responseYesterday.body()?.rt_nacional?.get(key)
                                if (rtTemp != null) {
                                    covidYesterday.rt = rtTemp
                                }
                               var localDayBeforeYesterday = local.getByDate(getPreviousDate(2))
                                if(localDayBeforeYesterday==null){
                                    val responseDayBeforeYesterday = service.getEntryByDate(getPreviousDate(2))
                                    if(responseDayBeforeYesterday.isSuccessful) {
                                        val key = getDaysDifference(getPreviousDate(2)).toString()
                                        val covidDayBeforeYesterday = CovidData(getPreviousDate(2))
                                        val confirmadosTemp = responseDayBeforeYesterday.body()?.confirmados?.get(key)
                                        if (confirmadosTemp != null) {
                                            covidDayBeforeYesterday.confirmados = confirmadosTemp
                                        }
                                        val recuperadosTemp = responseDayBeforeYesterday.body()?.recuperados?.get(key)
                                        if (recuperadosTemp != null) {
                                            covidDayBeforeYesterday.recuperados = recuperadosTemp
                                        }
                                        val obtiosTemp = responseDayBeforeYesterday.body()?.obitos?.get(key)
                                        if (obtiosTemp != null) {
                                            covidDayBeforeYesterday.obito = obtiosTemp
                                        }
                                        val internadosTemp = responseDayBeforeYesterday.body()?.internados?.get(key)
                                        if (internadosTemp != null) {
                                            covidDayBeforeYesterday.internados = internadosTemp
                                        }
                                        val internadosUciTemp = responseDayBeforeYesterday.body()?.internados_uci?.get(key)
                                        if (internadosUciTemp != null) {
                                            covidDayBeforeYesterday.internados_uci = internadosUciTemp
                                        }
                                        local?.insert(covidDayBeforeYesterday)
                                    }
                                }

                                var covidDayBeforeYesterday = local.getByDate(getPreviousDate(2))

                                if(covidDayBeforeYesterday!=null){
                                    covidYesterday.confirmados_novos = covidYesterday.confirmados - covidDayBeforeYesterday.confirmados
                                    covidYesterday.recuperados_novos = covidYesterday.recuperados - covidDayBeforeYesterday.recuperados
                                    covidYesterday.obitos_novos = covidYesterday.obito - covidDayBeforeYesterday.obito
                                    covidYesterday.internados_novos = covidYesterday.internados - covidDayBeforeYesterday.internados
                                    covidYesterday.internados_uci_novos = covidYesterday.internados_uci - covidDayBeforeYesterday.internados_uci
                                    local.insert(covidYesterday)
                                    notifyDashboardListeners(covidYesterday)
                                }
                            }
                        }else{
                            notifyDashboardListeners(localYesterday)

                        }
                    }
                }
            }else{
                notifyDashboardListeners(dateToday)
            }

        }



    }

    fun getVaccinesData() {

        val service = remote.create(VaccinesService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val sdf = SimpleDateFormat("dd-MM-yyyy")
            val currentDate = sdf.format(Date())

            val response = service.getLastUpdateVaccines()
            if (response.isSuccessful) {
                    val vaccinesToday = VaccinationData(currentDate)

                    val dosesTemp = response.body()?.size?.let { response.body()?.get(it-1)?.doses}
                    if (dosesTemp != null) {
                        vaccinesToday.doses = dosesTemp.toInt()
                    }

                    val dosesNovasTemp = response.body()?.size?.let { response.body()?.get(it-1)?.doses_novas}
                    if (dosesNovasTemp != null) {
                        vaccinesToday.doses_novas = dosesNovasTemp.toInt()
                    }

                    val doses1Temp = response.body()?.size?.let { response.body()?.get(it-1)?.doses1}
                    if (doses1Temp != null) {
                        vaccinesToday.doses1 = doses1Temp.toInt()
                    }

                    val doses1NovasTemp = response.body()?.size?.let { response.body()?.get(it-1)?.doses1_novas}
                    if (doses1NovasTemp != null) {
                        vaccinesToday.doses1_novas = doses1NovasTemp.toInt()
                    }

                    val doses2Temp = response.body()?.size?.let { response.body()?.get(it-1)?.doses2}
                    if (doses2Temp != null) {
                        vaccinesToday.doses2 = doses2Temp.toInt()
                    }

                    val doses2NovasTemp = response.body()?.size?.let { response.body()?.get(it-1)?.doses2_novas}
                    if (doses2NovasTemp != null) {
                        vaccinesToday.doses2_novas = doses2NovasTemp.toInt()
                    }
                    localVaccines.insert(vaccinesToday)
                    notifyVaccinesListeners(vaccinesToday)
            }


        }
    }

    fun getDaysDifference(date: String): Long {
        val sdf = SimpleDateFormat("dd-MM-yyyy")

        val startDate = Calendar.getInstance()
        val endDate = Calendar.getInstance()

        startDate[2020, Calendar.FEBRUARY] = 26
        endDate.time = sdf.parse(date)

        val diff = endDate.timeInMillis - startDate.timeInMillis

        return (diff / (24*60*60*1000)) + 1
    }

    fun getPreviousDate(days: Int) : String{
        val dateFormat: DateFormat = SimpleDateFormat("dd-MM-yyyy")
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -days)
        return dateFormat.format(cal.time).toString()
    }


        private val listeners = mutableListOf<FetchRepositoryListener>()


        fun registerDashboardListener(listener: FetchRepositoryListener) {

            listeners.add(listener)

        }


        fun unregisterDashboardListener(listener: FetchRepositoryListener) {

            listeners.remove(listener)

        }


        fun notifyDashboardListeners(dashboard: CovidData) {

            listeners.forEach { it.onRepositoryFetched(dashboard) }

        }

    private val listenersVaccines = mutableListOf<FetchRepositoryVaccinesListener>()


    fun registerVaccinesListener(listener: FetchRepositoryVaccinesListener) {

        listenersVaccines.add(listener)

    }


    fun unregisterVaccinesListener(listener: FetchRepositoryVaccinesListener) {

        listenersVaccines.remove(listener)

    }


    fun notifyVaccinesListeners(vaccination: VaccinationData) {

        listenersVaccines.forEach { it.onRepositoryVaccinesFetched(vaccination) }

    }


}


