package pt.ulusofona.deisi.a2020.cm.g4.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import io.github.dvegasa.arcpointer.ArcPointer
import kotlinx.android.synthetic.main.fragment_dashboard.*
import pt.ulusofona.deisi.a2020.cm.g4.R
import pt.ulusofona.deisi.a2020.cm.g4.ui.activities.current_level
import pt.ulusofona.deisi.a2020.cm.g4.ui.activities.danger_levels
import pt.ulusofona.deisi.a2020.cm.g4.data.DataSource

class DashboardFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
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

        confirmados_api.text = casasMilhares(DataSource().list_datas[1].confirmados.toString())
        obitos_api.text = casasMilhares(DataSource().list_datas[1].obitos.toString())
        recuperados_api.text = casasMilhares(DataSource().list_datas[1].recuperados.toString())
        internados_api.text = casasMilhares(DataSource().list_datas[1].internados.toString())
        internados_uci_api.text = casasMilhares(DataSource().list_datas[1].internados_uci.toString())

        confirmados_novos_api.text = "+" + DataSource().confirmados_novos.toString()
        obitos_novos_api.text = "+" + DataSource().obitos.toString()
        recuperados_novos_api.text = "+" + DataSource().recuperados.toString()
        if(DataSource().internados>0){
            internados_novos_api.text = "+" + DataSource().internados.toString()
        }else{
            internados_novos_api.text = DataSource().internados.toString()
        }

        if(DataSource().internados_uci>0){
            internados_uci_novos_api.text = "+" + DataSource().internados_uci.toString()
        }else{
            internados_uci_novos_api.text = DataSource().internados_uci.toString()
        }


        norte_api.text = "+" + DataSource().confirmados_arsnorte.toString()
        centro_api.text = "+" + DataSource().confirmados_arscentro.toString()
        lvt_api.text = "+" + DataSource().confirmados_arslvt.toString()
        alentejo_api.text = "+" + DataSource().confirmados_arsalentejo.toString()
        algarve_api.text = "+" + DataSource().confirmados_arsalgarve.toString()
        acores_api.text = "+" + DataSource().confirmados_acores.toString()
        madeira_api.text = "+" + DataSource().confirmados_madeira.toString()

         if(DataSource().rt_nacional<=1){
             rt_image.setImageDrawable(resources.getDrawable(R.drawable.ic_ok))
        }else{
             rt_image.setImageDrawable(resources.getDrawable(R.drawable.ic_bad))
        }


        rt_text.text = resources.getString(R.string.rt) + " = " + DataSource().rt_nacional

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