package pt.ulusofona.deisi.a2020.cm.g4.ui.fragments

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import io.github.dvegasa.arcpointer.ArcPointer
import kotlinx.android.synthetic.main.fragment_test_list.*
import kotlinx.android.synthetic.main.item_test.*
import pt.ulusofona.deisi.a2020.cm.g4.R
import pt.ulusofona.deisi.a2020.cm.g4.domain.test.Test
import pt.ulusofona.deisi.a2020.cm.g4.data.DataSource
import pt.ulusofona.deisi.a2020.cm.g4.ui.activities.EXTRA_TEST
import pt.ulusofona.deisi.a2020.cm.g4.ui.activities.TestDetailActivity
import pt.ulusofona.deisi.a2020.cm.g4.ui.activities.current_level
import pt.ulusofona.deisi.a2020.cm.g4.ui.activities.danger_levels
import pt.ulusofona.deisi.a2020.cm.g4.ui.adapters.TestAdapter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


var lista: ArrayList<Test>? = ArrayList<Test>()

class TestListFragment : Fragment(), TestAdapter.onTestItemClickListener, AdapterView.OnItemSelectedListener {
    private val TAG = TestListFragment::class.java.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_test_list, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            lista = arguments?.getParcelableArrayList<Test>(EXTRA_TEST)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        val text = parent.getItemAtPosition(pos).toString()
        var result: List<Test>
        if(text == "Descending" ){
            result = DataSource.tests.sortedByDescending { it.dateReg.toDate() }
            lista!!.clear()
            lista!!.addAll(result)
            list_test.layoutManager = LinearLayoutManager(activity as Context)
            list_test.adapter = lista?.let {
                TestAdapter(
                    activity as Context,
                    R.layout.item_test,
                    it,
                    this
                )
            }
        }else{
            result = DataSource.tests.sortedBy { it.dateReg.toDate() }
            lista!!.clear()
            lista!!.addAll(result)
            list_test.layoutManager = LinearLayoutManager(activity as Context)
            list_test.adapter = lista?.let {
                TestAdapter(
                    activity as Context,
                    R.layout.item_test,
                    it,
                    this
                )
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }

    override fun onStart(){
        super.onStart()

        list_test.layoutManager = LinearLayoutManager(activity as Context)
        list_test.adapter = lista?.let {
            TestAdapter(
                activity as Context,
                R.layout.item_test,
                it,
                this
            )
        }

        val arcPointer: ArcPointer = getView()!!.findViewById(R.id.arcpointer)
        arcPointer.value =
            current_level
        arcPointer.setNotches(3)
        val cores = listOf(Color.GREEN, Color.YELLOW, Color.RED)
        arcPointer.setNotchesColors(cores.toIntArray())
        arcPointer.setNotchesStrokeWidth(15.0f)
        arcPointer.setAnimated(true)
        arcPointer.setMarkerLengthRatio(0.2f)
        arcPointer.setLineLengthRatio(0.7f)
        arcPointer.setMarkerStrokeWidth(7.0f)
        arcPointer.setLineStrokeWidth(7.0f)

        if(current_level ==0.75f){
            current_level = danger_levels.get(0)
        }else{
            current_level = danger_levels.get(
                danger_levels.indexOf(
                    current_level
                )+1)
        }

        val spinner: Spinner = getView()!!.findViewById(R.id.sort_spinner)

        val adapter = ArrayAdapter.createFromResource(
            activity as Context,
            R.array.sort_array,
            R.layout.item_sort
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this
    }


    override fun onItemClick(item: Test, position: Int) {
        Log.i(TAG, "Click no Card $position")
        val intent = Intent(activity as Context, TestDetailActivity::class.java)
        intent.apply { putExtra("TESTDATE", item.date); putExtra("TESTRESULT", item.result); putExtra("TESTLOCAL", item.local); putExtra("TESTIMAGE", item.image?.absolutePath) }
        startActivity(intent)
    }

    fun String.toDate(): Date {
        return SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(this)
    }
}