package pt.ulusofona.deisi.a2020.cm.g4.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Test(val expression: String, val result: Double){

    @PrimaryKey
    var uuid: String = UUID.randomUUID().toString()
}