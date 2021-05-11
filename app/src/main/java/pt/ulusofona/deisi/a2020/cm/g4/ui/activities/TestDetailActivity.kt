package pt.ulusofona.deisi.a2020.cm.g4.ui.activities

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.dvegasa.arcpointer.ArcPointer
import kotlinx.android.synthetic.main.activity_test_detail.*
import pt.ulusofona.deisi.a2020.cm.g4.R


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

        val arcPointer: ArcPointer = findViewById(R.id.arcpointer)
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

    }


}