package pt.ulusofona.deisi.a2020.cm.g4.ui.fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import io.github.dvegasa.arcpointer.ArcPointer
import kotlinx.android.synthetic.main.fragment_vaccination.*
import pt.ulusofona.deisi.a2020.cm.g4.R
import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.entities.VaccinationData
import pt.ulusofona.deisi.a2020.cm.g4.ui.activities.current_level
import pt.ulusofona.deisi.a2020.cm.g4.ui.activities.danger_levels
import pt.ulusofona.deisi.a2020.cm.g4.ui.listeners.ReceiveVaccinesListener
import pt.ulusofona.deisi.a2020.cm.g4.ui.viewmodels.VaccinationViewModel

var POPULACAO_PORTUGUESA = 10295909

class VaccinationFragment: Fragment(), ReceiveVaccinesListener{

    private lateinit var viewModel : VaccinationViewModel


    private val TAG = VaccinationFragment::class.java.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_vaccination, container, false)
       viewModel = ViewModelProviders.of(this).get(VaccinationViewModel::class.java)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onStart() {
        super.onStart()
        val arcPointer: ArcPointer = getView()!!.findViewById(R.id.arcpointer)
        arcPointer.value = current_level
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

        viewModel.registerVaccinesListener(this)
        viewModel.getVaccines()



        simulation_button.setOnClickListener{
            Log.i(TAG, "Click button")
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://covid19.min-saude.pt/vacinacao/"))
            startActivity(i)
        }
    }

override fun onReceiveVaccines(vaccination: VaccinationData) {

        doses_api.text = truncateNumber(vaccination.doses.toFloat()).toString()
        doses_novas_api.text = "+" + casasMilhares(vaccination.doses_novas.toString())
        doses1_api.text = truncateNumber(vaccination.doses1.toFloat()).toString()
        doses1_perc_api.text = "%.2f".format((vaccination.doses1*100)/ POPULACAO_PORTUGUESA.toFloat()) + '%'
        doses2_api.text = truncateNumber(vaccination.doses2.toFloat()).toString()
        doses2_perc_api.text = "%.2f".format((vaccination.doses2*100)/ POPULACAO_PORTUGUESA.toFloat()) + '%'

}


    override fun onDestroy() {
        viewModel.unregisterVaccinesListener(this)
        super.onDestroy()
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

    fun truncateNumber(floatNumber: Float): String? {
        val thousand = 1000L
        val million = 1000000L
        val billion = 1000000000L
        val trillion = 1000000000000L
        val number = Math.round(floatNumber).toLong()
        if(number<million){
            val fraction = calculateFraction(number, thousand)
            return java.lang.Float.toString(fraction) + "m"
        }
        if (number >= million && number < billion) {
            val fraction = calculateFraction(number, million)
            return java.lang.Float.toString(fraction) + "M"
        } else if (number >= billion && number < trillion) {
            val fraction = calculateFraction(number, billion)
            return java.lang.Float.toString(fraction) + "B"
        }
        return java.lang.Long.toString(number)
    }

    fun calculateFraction(number: Long, divisor: Long): Float {
        val truncate = (number * 10L + divisor / 2L) / divisor
        return truncate.toFloat() * 0.10f
    }

}