package pt.ulusofona.deisi.a2020.cm.g4.data.remote.responses

import com.google.gson.annotations.SerializedName
import com.google.gson.internal.LinkedTreeMap

class DataResponseByDate(
        @SerializedName("data") var data: LinkedTreeMap<String, String>,
        @SerializedName("confirmados") var confirmados: LinkedTreeMap<String, Int>,
        @SerializedName("confirmados_arsnorte") var confirmados_arsnorte: LinkedTreeMap<String, Int>,
        @SerializedName("confirmados_arscentro") var confirmados_arscentro: LinkedTreeMap<String, Int> ,
        @SerializedName("confirmados_arslvt") var confirmados_arslvt: LinkedTreeMap<String, Int>,
        @SerializedName("confirmados_arsalentejo") var confirmados_arsalentejo: LinkedTreeMap<String, Int>,
        @SerializedName("confirmados_arsalgarve") var confirmados_arsalgarve: LinkedTreeMap<String, Int>,
        @SerializedName("confirmados_acores") var confirmados_acores: LinkedTreeMap<String, Int>,
        @SerializedName("confirmados_madeira") var confirmados_madeira: LinkedTreeMap<String, Int>,
        @SerializedName("recuperados") var recuperados: LinkedTreeMap<String, Int>,
        @SerializedName("obitos") var obitos: LinkedTreeMap<String, Int>,
        @SerializedName("internados") var internados: LinkedTreeMap<String, Int>,
        @SerializedName("internados_uci") var internados_uci: LinkedTreeMap<String, Int>,
        @SerializedName("rt_nacional") var rt_nacional: LinkedTreeMap<String, Double>
)

