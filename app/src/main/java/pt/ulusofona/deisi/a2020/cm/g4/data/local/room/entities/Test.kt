package pt.ulusofona.deisi.a2020.cm.g4.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.io.File
import java.util.*

@Entity
data class Test(val date: String, val result: String, val local: String,  val image: String?, val dateReg: String){

    @PrimaryKey
    var uuid: String = UUID.randomUUID().toString()
}