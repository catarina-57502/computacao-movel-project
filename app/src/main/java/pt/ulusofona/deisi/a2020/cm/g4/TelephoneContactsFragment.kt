package pt.ulusofona.deisi.a2020.cm.g4

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.OnClick
import butterknife.Optional
import kotlinx.android.synthetic.main.fragment_telephone_contacts.*

private val TAG = TelephoneContactsFragment::class.java.simpleName

class TelephoneContactsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_telephone_contacts, container, false)
    }

    override fun onStart() {
        super.onStart()

        telephone_tab.setOnClickListener {
            Log.i(TAG, "Click Telephone tab")
            NavigationManager.goToTelephoneContactsFragment(fragmentManager!!)

        }

        digital_tab.setOnClickListener {
            Log.i(TAG, "Click Digital tab")
            NavigationManager.goToDigitalContactsFragment(fragmentManager!!)

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