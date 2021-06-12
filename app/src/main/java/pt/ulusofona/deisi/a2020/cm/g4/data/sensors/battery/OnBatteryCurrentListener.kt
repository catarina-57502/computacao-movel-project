package pt.ulusofona.deisi.a2020.cm.g4.data.sensors.battery

interface OnBatteryCurrentListener {
    fun onCurrentChanged(current: Float?)
}