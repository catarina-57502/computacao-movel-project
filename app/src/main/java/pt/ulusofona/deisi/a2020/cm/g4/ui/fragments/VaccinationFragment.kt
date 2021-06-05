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
import io.github.dvegasa.arcpointer.ArcPointer
import kotlinx.android.synthetic.main.fragment_vaccination.*
import pt.ulusofona.deisi.a2020.cm.g4.R
import pt.ulusofona.deisi.a2020.cm.g4.data.local.list.DataSource
import pt.ulusofona.deisi.a2020.cm.g4.ui.activities.current_level
import pt.ulusofona.deisi.a2020.cm.g4.ui.activities.danger_levels

class VaccinationFragment : Fragment() {

    private val TAG = VaccinationFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vaccination, container, false)
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

        doses_api.text = truncateNumber(DataSource().doses.toFloat()).toString()
        doses_novas_api.text = "+" + DataSource().doses_novas.toString()
        doses1_api.text = truncateNumber(DataSource().doses1.toFloat()).toString()
        doses1_perc_api.text = (DataSource().doses1_perc * 100).toString() + '%'
        doses2_api.text = truncateNumber(DataSource().doses2.toFloat()).toString()
        doses2_perc_api.text = (DataSource().doses2_perc * 100).toString() + '%'

        simulation_button.setOnClickListener{
            Log.i(TAG, "Click button")
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://covid19.min-saude.pt/vacinacao/"))
            startActivity(i)
        }
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