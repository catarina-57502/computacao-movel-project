package pt.ulusofona.deisi.a2020.cm.g4.domain.test

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Test(val date: String, val result: String, val local: String, val image: String, val dateReg: String) : Parcelable