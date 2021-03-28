package pt.ulusofona.deisi.a2020.cm.g4


import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_test_list.*
import java.text.FieldPosition


class TestListActivity : AppCompatActivity(), TestAdapter.onTestItemClickListener {

    private val TAG = TestListActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_list)

    }

    override fun onStart(){
        super.onStart()
        val lista = intent.getParcelableArrayListExtra<Test>(EXTRA_TEST)

        list_test.layoutManager = LinearLayoutManager(this)
        list_test.adapter = TestAdapter(this, R.layout.item_test, lista, this)

        button_back.setOnClickListener{
            val intent = Intent(this, TestRegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun onClickTest(item: Test, position: Int){
        Log.i(TAG, "Click no Card $position")
        val intent = Intent(this, TestDetailActivity::class.java)
        intent.apply { putExtra(EXTRA_TEST, item.date); putExtra(EXTRA_TEST, item.result); putExtra(EXTRA_TEST, item.local) }
        startActivity(intent)
        finish()
    }

    override fun onItemClick(item: Test, position: Int) {
        //Toast.makeText(this, item.result , Toast.LENGTH_SHORT).show()
        Log.i(TAG, "Click no Card $position")
        val intent = Intent(this, TestDetailActivity::class.java)
        intent.apply { putExtra("TESTDATE", item.date); putExtra("TESTRESULT", item.result); putExtra("TESTLOCAL", item.local) }
        startActivity(intent)
        finish()
    }
}