package pt.ulusofona.deisi.a2020.cm.g4.ui.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import pt.ulusofona.deisi.a2020.cm.g4.R
import pt.ulusofona.deisi.a2020.cm.g4.ui.fragments.*

class NavigationManager {

    companion object{
        private fun placeFragment(fm: FragmentManager, fragment: Fragment){
            val transition = fm.beginTransaction()
            //fragment.arguments = bundle
            transition.replace(R.id.frame, fragment)
            transition.addToBackStack(null)
            transition.commit()
        }

        fun goToDigitalContactsFragment(fm: FragmentManager){
            placeFragment(
                fm,
                DigitalContactsFragment()
            )

        }

        fun goToTelephoneContactsFragment(fm: FragmentManager){
            placeFragment(
                fm,
                TelephoneContactsFragment()
            )

        }

         fun goToDashboardFragment(fm: FragmentManager){
             placeFragment(
                 fm,
                 DashboardFragment()
             )

        }
        fun goToContactsFragment(fm: FragmentManager){
            placeFragment(
                fm,
                ContactsFragment()
            )

        }

        fun goToVaccinationFragment(fm: FragmentManager){
            placeFragment(
                fm,
                VaccinationFragment()
            )
        }

        fun goToTestRegisterFragment(fm: FragmentManager){
            placeFragment(
                fm,
                TestRegisterFragment()
            )

        }

        fun goToTestListFragment(fm: FragmentManager){
            placeFragment(
                fm,
                TestListFragment()
            )

        }
    }

}