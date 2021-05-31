package pt.ulusofona.deisi.a2020.cm.g4.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class CovidData(var data: String) {

    @PrimaryKey
    var uuid: String = UUID.randomUUID().toString()

    var confirmados = 0
    var obito = 0
    var recuperados = 0
    var internados = 0
    var internados_uci= 0
    var confirmados_novos = 0
    var obitos_novos = 0
    var recuperados_novos = 0
    var internados_novos = 0
    var internados_uci_novos = 0
    var norte = 0
    var centro = 0
    var lvt = 0
    var alentejo = 0
    var algarve = 0
    var acores = 0
    var madeira = 0
    var rt = 0.0


}