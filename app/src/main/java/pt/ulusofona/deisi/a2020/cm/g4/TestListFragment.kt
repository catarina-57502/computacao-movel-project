package pt.ulusofona.deisi.a2020.cm.g4

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import io.github.dvegasa.arcpointer.ArcPointer
import kotlinx.android.synthetic.main.fragment_test_list.*

var lista: ArrayList<Test>? = ArrayList<Test>()

class TestListFragment : Fragment(), TestAdapter.onTestItemClickListener {
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


    override fun onStart(){
        super.onStart()

        list_test.layoutManager = LinearLayoutManager(activity as Context)
        list_test.adapter = lista?.let { TestAdapter(activity as Context, R.layout.item_test, it, this) }

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

    }


    override fun onItemClick(item: Test, position: Int) {
        Log.i(TAG, "Click no Card $position")
        val intent = Intent(activity as Context, TestDetailActivity::class.java)
        intent.apply { putExtra("TESTDATE", item.date); putExtra("TESTRESULT", item.result); putExtra("TESTLOCAL", item.local) }
        startActivity(intent)
    }
}