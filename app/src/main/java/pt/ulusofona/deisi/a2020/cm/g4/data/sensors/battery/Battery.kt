package pt.ulusofona.deisi.a2020.cm.g4.data.sensors.battery

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate

class Battery(private val context: Context) : Runnable {

    private val TIME_BETWEEN_UPDATES = 20*1000L

    companion object{
        private var instance: Battery? = null
        private val handler = Handler()

        fun start(context: Context){
            instance = if(instance==null) Battery(context) else instance
            instance?.start()
        }
    }

    private fun start(){
        handler.postDelayed(this, TIME_BETWEEN_UPDATES)
    }

    fun getBatteryCurrentNow() : Float?{
        val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { ifilter ->
            context.registerReceiver(null, ifilter)
        }
        val batteryPct: Float? = batteryStatus?.let { intent ->
            val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            level * 100 / scale.toFloat()
        }
        return batteryPct
    }

    override fun run(){
        val current = getBatteryCurrentNow()
        println(current.toString())
        handler.postDelayed(this, TIME_BETWEEN_UPDATES)
    }

}