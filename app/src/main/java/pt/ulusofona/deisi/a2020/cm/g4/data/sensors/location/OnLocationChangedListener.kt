package pt.ulusofona.deisi.a2020.cm.g4.data.sensors.location

import com.google.android.gms.location.LocationResult

interface OnLocationChangedListener {
    fun onLocationChanged(location: LocationResult)
}