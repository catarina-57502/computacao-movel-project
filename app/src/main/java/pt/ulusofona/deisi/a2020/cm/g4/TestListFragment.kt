package pt.ulusofona.deisi.a2020.cm.g4

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.OnClick
import kotlinx.android.synthetic.main.fragment_test_list.*
import pt.ulusofona.deisi.a2020.cm.g4.DataSource.Companion.tests

var lista: ArrayList<Test>? = ArrayList<Test>()

class TestListFragment : Fragment(), TestAdapter.onTestItemClickListener {
    private val TAG = TestListActivity::class.java.simpleName

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


        button_back.setOnClickListener{
            fragmentManager?.let { it1 -> NavigationManager.goToTestRegisterFragment(it1, null) }
        }
    }


    override fun onItemClick(item: Test, position: Int) {
        Log.i(TAG, "Click no Card $position")
        val intent = Intent(activity as Context, TestDetailActivity::class.java)
        intent.apply { putExtra("TESTDATE", item.date); putExtra("TESTRESULT", item.result); putExtra("TESTLOCAL", item.local) }
        startActivity(intent)
    }
}