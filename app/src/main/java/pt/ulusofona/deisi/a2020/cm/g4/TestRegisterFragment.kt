package pt.ulusofona.deisi.a2020.cm.g4

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color.*
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.OnClick
import io.github.dvegasa.arcpointer.ArcPointer
import kotlinx.android.synthetic.main.fragment_test_register.*
import pt.ulusofona.deisi.a2020.cm.g4.data.DataSource
import java.text.SimpleDateFormat
import java.util.*


class TestRegisterFragment : Fragment() {

    private val TAG = TestRegisterFragment::class.java.simpleName
    private val DATE_KEY = "date"
    private val RESULT_KEY = "result"
    private val LOCAL_KEY = "local"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_test_register, container, false)
        ButterKnife.bind(this, view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val textView: TextView = getView()!!.findViewById(R.id.date_input)
        textView.text = SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())

        val cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy"
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            textView.text = sdf.format(cal.time)

        }

        textView.setOnClickListener {
            DatePickerDialog(activity as Context, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    override fun onStart() {
        super.onStart()
        val arcPointer: ArcPointer = getView()!!.findViewById(R.id.arcpointer)
        arcPointer.value = current_level
        arcPointer.setNotches(3)
        val cores = listOf(GREEN, YELLOW, RED)
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


    @OnClick(R.id.submit)
    fun onClickSubmit(view: View){
        Log.i(TAG, "Click no botão Submit")
        val result: RadioButton? = getView()?.findViewById(result_input.checkedRadioButtonId)
        when {
            result==null && local_input.text.toString().isBlank() -> {
                Toast.makeText(activity, resources.getString(R.string.invalid_local_result), Toast.LENGTH_SHORT).show()
            }
            result==null -> {
                Toast.makeText(activity, resources.getString(R.string.invalid_result), Toast.LENGTH_SHORT).show()
            }
            local_input.text.toString().isBlank() -> {
                Toast.makeText(activity, resources.getString(R.string.invalid_local), Toast.LENGTH_SHORT).show()
            }
            else -> {
                val builder = AlertDialog.Builder(activity as Context)

                var alertDialog: AlertDialog? = null

                builder.setTitle(R.string.dialogTitle)
                builder.setMessage(R.string.dialogMessage)
                builder.setIcon(R.drawable.ic_submit)

                builder.setPositiveButton(resources.getString(R.string.submit)){dialogInterface, which ->
                    DataSource.tests.add(Test(date_input.text.toString(), result.text.toString(), local_input.text.toString(), photo_input.text.toString(), SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(
                        Calendar.getInstance().time)))
                }
                builder.setNegativeButton(resources.getString(R.string.cancel)){dialogInterface , which ->
                    alertDialog!!.dismiss()
                }

                alertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()

                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setBackgroundColor(resources.getColor(R.color.blue))
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(resources.getColor(R.color.white))
            }
        }
    }

}