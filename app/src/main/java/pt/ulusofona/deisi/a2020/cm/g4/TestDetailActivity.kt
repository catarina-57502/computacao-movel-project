package pt.ulusofona.deisi.a2020.cm.g4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_test_detail.*
import kotlinx.android.synthetic.main.activity_test_list.*

private val TAG = TestDetailActivity::class.java.simpleName

class TestDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_detail)
    }

    override fun onStart(){
        super.onStart()

        detail_date.text = getIntent().getStringExtra("TESTDATE")
        detail_result.text = getIntent().getStringExtra("TESTRESULT")
        detail_local.text = getIntent().getStringExtra("TESTLOCAL")


        button_back2.setOnClickListener{
            val intent = Intent(this, TestRegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}