package pt.ulusofona.deisi.a2020.cm.g4.ui.fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.dvegasa.arcpointer.ArcPointer
import kotlinx.android.synthetic.main.fragment_telephone_contacts.*
import pt.ulusofona.deisi.a2020.cm.g4.ui.utils.NavigationManager
import pt.ulusofona.deisi.a2020.cm.g4.R
import pt.ulusofona.deisi.a2020.cm.g4.ui.activities.current_level
import pt.ulusofona.deisi.a2020.cm.g4.ui.activities.danger_levels

private val TAG = TelephoneContactsFragment::class.java.simpleName

class TelephoneContactsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_telephone_contacts, container, false)
    }

    override fun onStart() {
        super.onStart()

        telephone_tab.setOnClickListener {
            Log.i(TAG, "Click Telephone tab")
            NavigationManager.goToTelephoneContactsFragment(
                fragmentManager!!
            )

        }

        digital_tab.setOnClickListener {
            Log.i(TAG, "Click Digital tab")
            NavigationManager.goToDigitalContactsFragment(
                fragmentManager!!
            )

        }

        number1.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${number1.tag}")
            startActivity(Intent(Intent.ACTION_DIAL))
            startActivity(intent)
        }

        number2.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${number2.tag}")
            startActivity(Intent(Intent.ACTION_DIAL))
            startActivity(intent)
        }

        number3.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${number3.tag}")
            startActivity(Intent(Intent.ACTION_DIAL))
            startActivity(intent)
        }


        number4.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${number4.tag}")
            startActivity(Intent(Intent.ACTION_DIAL))
            startActivity(intent)
        }

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

    }

    /*

    @OnClick(R.id.number1, R.id.number2, R.id.number3, R.id.number4)
    fun onClickNumber(view: View){
        val number = view.tag.toString()
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$number")
        startActivity(Intent(Intent.ACTION_DIAL))
        startActivity(intent)
    }
          */

}