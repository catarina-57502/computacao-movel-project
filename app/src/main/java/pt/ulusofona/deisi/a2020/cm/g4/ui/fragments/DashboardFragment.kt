package pt.ulusofona.deisi.a2020.cm.g4.ui.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import com.google.android.gms.location.LocationResult
import io.github.dvegasa.arcpointer.ArcPointer
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_vaccination.*
import pt.ulusofona.deisi.a2020.cm.g4.R
import pt.ulusofona.deisi.a2020.cm.g4.ui.activities.current_level
import pt.ulusofona.deisi.a2020.cm.g4.ui.activities.danger_levels
import pt.ulusofona.deisi.a2020.cm.g4.data.local.list.DataSource
import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.entities.CovidData
import pt.ulusofona.deisi.a2020.cm.g4.data.sensors.location.FusedLocation
import pt.ulusofona.deisi.a2020.cm.g4.data.sensors.location.OnLocationChangedListener
import pt.ulusofona.deisi.a2020.cm.g4.ui.fragments.premissions.PermissionedFragment
import pt.ulusofona.deisi.a2020.cm.g4.ui.listeners.ReceiveDashboardListener
import pt.ulusofona.deisi.a2020.cm.g4.ui.viewmodels.DashboardViewModel
import java.util.jar.Manifest

const val REQUEST_CODE_MAP = 100

class DashboardFragment : ReceiveDashboardListener, PermissionedFragment(REQUEST_CODE_MAP), OnLocationChangedListener {

    private lateinit var viewModel : DashboardViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onStart() {
        super.onRequestPermissions(activity?.baseContext!!, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION))
        super.onStart()


        val arcPointer: ArcPointer = getView()!!.findViewById(R.id.arcpointer)
        arcPointer.value =
            current_level
        arcPointer.setNotches(4)
        val cores = listOf(Color.GREEN, Color.YELLOW, Color.rgb(255, 165, 0), Color.RED)
        arcPointer.setNotchesColors(cores.toIntArray())
        arcPointer.setNotchesStrokeWidth(15.0f)
        arcPointer.setAnimated(true)
        arcPointer.setMarkerLengthRatio(0.2f)
        arcPointer.setLineLengthRatio(0.7f)
        arcPointer.setMarkerStrokeWidth(7.0f)
        arcPointer.setLineStrokeWidth(7.0f)

        if(current_level ==0.8f){
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
        viewModel.unregisterDashboardListener(this)
        super.onDestroy()
    }

    override fun onReceiveDashboard(dashboard: CovidData) {

        confirmados_api.text = casasMilhares(dashboard.confirmados.toString())
        obitos_api.text = casasMilhares(dashboard.obito.toString())
        recuperados_api.text = casasMilhares(dashboard.recuperados.toString())
        internados_api.text = casasMilhares(dashboard.internados.toString())
        internados_uci_api.text = casasMilhares(dashboard.internados_uci.toString())
        if(dashboard.confirmados_novos>0){
            confirmados_novos_api.text = "+" + dashboard.confirmados_novos.toString()
        }else{
            confirmados_novos_api.text = dashboard.confirmados_novos.toString()
        }
        if(dashboard.obitos_novos>0){
            obitos_novos_api.text = "+" + dashboard.obitos_novos.toString()
        }else{
            obitos_novos_api.text = dashboard.obitos_novos.toString()
        }
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
        norte_api.text = casasMilhares(dashboard.norte.toString())
        centro_api.text = casasMilhares(dashboard.centro.toString())
        lvt_api.text = casasMilhares(dashboard.lvt.toString())
        alentejo_api.text = casasMilhares(dashboard.alentejo.toString())
        algarve_api.text = casasMilhares(dashboard.algarve.toString())
        acores_api.text = casasMilhares(dashboard.acores.toString())
        madeira_api.text = casasMilhares(dashboard.madeira.toString())
        if(dashboard.rt <= 1){
            rt_image.setImageDrawable(resources.getDrawable(R.drawable.ic_ok))
        }else{
            rt_image.setImageDrawable(resources.getDrawable(R.drawable.ic_bad))
        }
        rt_text.text = resources.getString(R.string.rt) + " = " + dashboard.rt.toString()

        data_update.text = dashboard.data

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

    override fun onRequestPermissionsSucess() {
        FusedLocation.registerListener(this)
    }

    override fun onRequestPermissionsFailure() {
    }

    override fun onLocationChanged(location: LocationResult) {
        val location = location.lastLocation
    }
}