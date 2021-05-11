package pt.ulusofona.deisi.a2020.cm.g4.domain.test

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.File

@Parcelize
class Test(val date: String, val result: String, val local: String, val image: File, val dateReg: String) : Parcelable