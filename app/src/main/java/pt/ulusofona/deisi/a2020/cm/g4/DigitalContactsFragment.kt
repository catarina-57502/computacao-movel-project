package pt.ulusofona.deisi.a2020.cm.g4

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.OnClick
import io.github.dvegasa.arcpointer.ArcPointer
import kotlinx.android.synthetic.main.fragment_digital_contacts.*
import kotlinx.android.synthetic.main.fragment_telephone_contacts.*
import kotlinx.android.synthetic.main.fragment_telephone_contacts.digital_tab
import kotlinx.android.synthetic.main.fragment_telephone_contacts.telephone_tab


class DigitalContactsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_digital_contacts, container, false)
    }

    override fun onStart() {
        super.onStart()

        telephone_tab.setOnClickListener {
            NavigationManager.goToTelephoneContactsFragment(fragmentManager!!)
        }

        digital_tab.setOnClickListener {
            NavigationManager.goToDigitalContactsFragment(fragmentManager!!)
        }

        site1.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(site1.tag.toString())
            startActivity(Intent(Intent.ACTION_VIEW))
            startActivity(intent)
        }

        site2.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(site2.tag.toString())
            startActivity(Intent(Intent.ACTION_VIEW))
            startActivity(intent)
        }

        mail1.setOnClickListener{
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:${mail1.text}")
            startActivity(intent)
        }

        mail2.setOnClickListener{
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:${mail2.text}")
            startActivity(intent)
        }

        val arcPointer: ArcPointer = getView()!!.findViewById(R.id.arcpointer)
        arcPointer.value = current_level
        arcPointer.setNotches(3)
        val cores = listOf(Color.GREEN, Color.YELLOW, Color.RED)
        arcPointer.setNotchesColors(cores.toIntArray())
        arcPointer.setNotchesStrokeWidth(15.0f)
        arcPointer.setAnimated(true)
        arcPointer.setMarkerLengthRatio(0.2f)
        arcPointer.setLineLengthRatio(0.7f)
        arcPointer.setMarkerStrokeWidth(7.0f)
        arcPointer.setLineStrokeWidth(7.0f)

        if(current_level==0.75f){
            current_level = danger_levels.get(0)
        }else{
            current_level = danger_levels.get(danger_levels.indexOf(current_level)+1)
        }

    }

}