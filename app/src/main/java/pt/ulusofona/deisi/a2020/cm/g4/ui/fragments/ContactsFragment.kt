package pt.ulusofona.deisi.a2020.cm.g4.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pt.ulusofona.deisi.a2020.cm.g4.ui.utils.NavigationManager
import pt.ulusofona.deisi.a2020.cm.g4.R

class ContactsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val view = inflater.inflate(R.layout.fragment_contacts, container, false)

        if(!screenRotated(savedInstanceState)){
            NavigationManager.goToTelephoneContactsFragment(
                fragmentManager!!
            )
        }

        return view
    }

    private fun screenRotated(savedInstanceState: Bundle?) : Boolean{
        return savedInstanceState != null
    }
}