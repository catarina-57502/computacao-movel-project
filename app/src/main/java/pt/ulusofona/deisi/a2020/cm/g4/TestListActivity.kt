package pt.ulusofona.deisi.a2020.cm.g4


import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_test_list.*


class TestListActivity : AppCompatActivity() {

    private val TAG = TestListActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_list)

    }

    override fun onStart(){
        super.onStart()
        val lista = intent.getParcelableArrayListExtra<Test>(EXTRA_TEST)

        list_test.layoutManager = LinearLayoutManager(this)
        list_test.adapter = TestAdapter(this, R.layout.item_test, lista)

        button_back.setOnClickListener{
            val intent = Intent(this, TestRegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}