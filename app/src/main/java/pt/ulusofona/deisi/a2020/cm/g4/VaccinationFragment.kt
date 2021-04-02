package pt.ulusofona.deisi.a2020.cm.g4

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.OnClick
import io.github.dvegasa.arcpointer.ArcPointer
import kotlinx.android.synthetic.main.fragment_vaccination.*
import pt.ulusofona.deisi.a2020.cm.g4.data.DataSource

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
        arcPointer.value = 0.25f
        arcPointer.setNotches(3)
        val cores = listOf(Color.GREEN, Color.YELLOW, Color.RED)
        arcPointer.setNotchesColors(cores.toIntArray())
        arcPointer.setNotchesStrokeWidth(15.0f)
        arcPointer.setAnimated(true)
        arcPointer.setMarkerLengthRatio(0.2f)
        arcPointer.setLineLengthRatio(0.7f)
        arcPointer.setMarkerStrokeWidth(7.0f)
        arcPointer.setLineStrokeWidth(7.0f)

        doses_api.text = DataSource().doses.toString()
        doses_novas_api.text = DataSource().doses_novas.toString()
        doses1_api.text = DataSource().doses1.toString()
        doses1_perc_api.text = (DataSource().doses1_perc * 100).toString() + '%'
        doses2_api.text = DataSource().doses2.toString()
        doses2_perc_api.text = (DataSource().doses2_perc * 100).toString() + '%'

        simulation_button.setOnClickListener{
            Log.i(TAG, "Click button")
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://covid19.min-saude.pt/vacinacao/"))
            startActivity(i)
        }
    }

}