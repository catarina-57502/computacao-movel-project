package pt.ulusofona.deisi.a2020.cm.g4.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import io.github.dvegasa.arcpointer.ArcPointer
import kotlinx.android.synthetic.main.fragment_dashboard.*
import pt.ulusofona.deisi.a2020.cm.g4.R
import pt.ulusofona.deisi.a2020.cm.g4.ui.activities.current_level
import pt.ulusofona.deisi.a2020.cm.g4.ui.activities.danger_levels
import pt.ulusofona.deisi.a2020.cm.g4.data.local.list.DataSource
import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.entities.CovidData
import pt.ulusofona.deisi.a2020.cm.g4.ui.listeners.ReceiveDashboardListener
import pt.ulusofona.deisi.a2020.cm.g4.ui.viewmodels.DashboardViewModel

class DashboardFragment : Fragment(), ReceiveDashboardListener {

    private lateinit var viewModel : DashboardViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onStart() {
        super.onStart()
        val arcPointer: ArcPointer = getView()!!.findViewById(R.id.arcpointer)
        arcPointer.value =
            current_level
        arcPointer.setNotches(3)
        val cores = listOf(Color.GREEN, Color.YELLOW, Color.RED)
        arcPointer.setNotchesColors(cores.toIntArray())
        arcPointer.setNotchesStrokeWidth(15.0f)
        arcPointer.setAnimated(true)
        arcPointer.setMarkerLengthRatio(0.2f)
        arcPointer.setLineLengthRatio(0.7f)
        arcPointer.setMarkerStrokeWidth(7.0f)
        arcPointer.setLineStrokeWidth(7.0f)

        if(current_level ==0.75f){
            current_level = danger_levels.get(0)
        }else{
            current_level = danger_levels.get(
                danger_levels.indexOf(
                    current_level
                )+1)
        }

        viewModel.registerDashboardListener(this)

        viewModel.getDashboard()



    }

    override fun onDestroy() {
        super.onDestroy()
        //DashboardViewModel.unregisterDashboardListener(this)
    }

    override fun onReceiveDashboard(dashboard: CovidData) {
        confirmados_api.text = casasMilhares(dashboard.confirmados.toString())
        obitos_api.text = casasMilhares(dashboard.obito.toString())
        recuperados_api.text = casasMilhares(dashboard.recuperados.toString())
        internados_api.text = casasMilhares(dashboard.internados.toString())
        internados_uci_api.text = casasMilhares(dashboard.internados_uci.toString())
        confirmados_novos_api.text = "+" + dashboard.confirmados_novos.toString()
        obitos_novos_api.text = "+" + dashboard.obitos_novos.toString()
        recuperados_novos_api.text = "+" + dashboard.recuperados_novos.toString()
        if(dashboard.internados_novos>0){
            internados_novos_api.text = "+" + dashboard.internados_novos.toString()
        }else{
            internados_novos_api.text = dashboard.internados_novos.toString()
        }

        if(dashboard.internados_uci_novos>0){
            internados_uci_novos_api.text = "+" + dashboard.internados_uci_novos.toString()
        }else{
            internados_uci_novos_api.text = dashboard.internados_uci_novos.toString()
        }
        norte_api.text = dashboard.norte.toString()
        centro_api.text = dashboard.centro.toString()
        lvt_api.text = dashboard.lvt.toString()
        alentejo_api.text = dashboard.alentejo.toString()
        algarve_api.text = dashboard.algarve.toString()
        acores_api.text = dashboard.acores.toString()
        madeira_api.text = dashboard.madeira.toString()
        if(dashboard.rt <= 1){
            rt_image.setImageDrawable(resources.getDrawable(R.drawable.ic_ok))
        }else{
            rt_image.setImageDrawable(resources.getDrawable(R.drawable.ic_bad))
        }
        rt_text.text = resources.getString(R.string.rt) + " = " + dashboard.rt.toString()
    }

    fun casasMilhares(number: String) : String{

        if(number.length==6){
            return number.substring(0, 3) + '.' + number.substring(3)
        }
        if(number.length==5){
            return number.substring(0, 2) + '.' + number.substring(2)
        }
        if(number.length==4){
            return number.substring(0, 1) + '.' + number.substring(1)
        }
        return number
    }
}