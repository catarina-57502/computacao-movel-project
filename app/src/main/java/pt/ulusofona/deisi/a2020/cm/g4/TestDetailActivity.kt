package pt.ulusofona.deisi.a2020.cm.g4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test_detail.*


private val TAG = TestDetailActivity::class.java.simpleName

class TestDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_detail)

        setSupportActionBar(toolbar_back)

        supportActionBar?.apply {
            title = getString(R.string.test_details);
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun onStart(){
        super.onStart()

        detail_date.text = getIntent().getStringExtra("TESTDATE")
        detail_result.text = getIntent().getStringExtra("TESTRESULT")
        detail_local.text = getIntent().getStringExtra("TESTLOCAL")
    }


}