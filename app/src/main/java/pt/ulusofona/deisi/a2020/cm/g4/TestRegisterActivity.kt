package pt.ulusofona.deisi.a2020.cm.g4

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_test_register.*
import kotlinx.android.synthetic.main.item_test.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

const val EXTRA_TEST = "pt.ulusofona.deisi.a2020.cm.g4.TEST"

class TestRegisterActivity : AppCompatActivity() {

    private val TAG = TestRegisterActivity::class.java.simpleName
    private var tests = ArrayList<Test>()
    private val DATE_KEY = "date"
    private val RESULT_KEY = "result"
    private val LOCAL_KEY = "local"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_register)

        val textView: TextView = findViewById(R.id.date_input)
        textView.text = SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())

        var cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy"
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            textView.text = sdf.format(cal.time)

        }

        textView.setOnClickListener {
            DatePickerDialog(this, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        date_input.setText(savedInstanceState.getString(DATE_KEY))
        result_input.check(savedInstanceState.getInt(RESULT_KEY))
        local_input.setText(savedInstanceState.getString(LOCAL_KEY))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run { putString(DATE_KEY,  date_input.text.toString()); putInt(RESULT_KEY,  result_input.checkedRadioButtonId); putString(LOCAL_KEY,  local_input.text.toString()) }
        super.onSaveInstanceState(outState)
    }

    fun onClickSubmit(view: View){
        Log.i(TAG, "Click no botão Submit")
        val result: RadioButton = findViewById(result_input.checkedRadioButtonId)
        Log.i(TAG, result.text.toString())
        tests.add(Test(date_input.text.toString(), result.text.toString(), local_input.text.toString(), photo_input.text.toString(), SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Calendar.getInstance().time)))
        val intent = Intent(this, TestListActivity::class.java)
        intent.apply { putParcelableArrayListExtra(EXTRA_TEST, tests) }
        startActivity(intent)
        finish()

    }

}