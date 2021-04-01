package pt.ulusofona.deisi.a2020.cm.g4

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class NavigationManager {

    companion object{
        private fun placeFragment(fm: FragmentManager, fragment: Fragment, bundle: Bundle?){
            val transition = fm.beginTransaction()
            fragment.arguments = bundle
            transition.replace(R.id.frame, fragment)
            transition.addToBackStack(null)
            transition.commit()
        }

        /*
         fun goToDashboardFragment(fm: FragmentManager, bundle: Bundle?){
            placeFragment(fm, DashboardFragment(), bundle)

        }

        fun goToContactsFragment(fm: FragmentManager, bundle: Bundle?){
            placeFragment(fm, ContactsFragment(), bundle)

        }
         */

        fun goToVaccinationFragment(fm: FragmentManager){
            placeFragment(fm, TestRegisterFragment(), null)
        }

        fun goToTestRegisterFragment(fm: FragmentManager){
            placeFragment(fm, TestRegisterFragment(), null)

        }

        fun goToTestListFragment(fm: FragmentManager, bundle: Bundle?){
            placeFragment(fm, TestListFragment(), bundle)

        }
    }

}