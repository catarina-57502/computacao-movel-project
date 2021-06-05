package pt.ulusofona.deisi.a2020.cm.g4.data.remote.requests

import com.google.gson.annotations.SerializedName

data class Data(
    /***********************************Dashboard*******************************************/
    @SerializedName("data") val data: String,
    @SerializedName("confirmados") val confirmados:String,
    @SerializedName("confirmados_arsnorte") val confirmados_arsnorte:String,
    @SerializedName("confirmados_arscentro") val confirmados_arscentro:String,
    @SerializedName("confirmados_arslvt") val confirmados_arslvt:String,
    @SerializedName("confirmados_arsalentejo") val confirmados_arsalentejo:String,
    @SerializedName("confirmados_arsalgarve") val confirmados_arsalgarve:String,
    @SerializedName("confirmados_acores") val confirmados_acores:String,
    @SerializedName("confirmados_madeira") val confirmados_madeira:String,
    @SerializedName("recuperados") val recuperados:String,
    @SerializedName("obitos") val obitos:String,
    @SerializedName("internados") val internados:String,
    @SerializedName("internados_uci") val internados_uci:String,
    @SerializedName("rt_nacional") val rt_nacional:String,

    /***********************************Vacinas*******************************************/
    @SerializedName("doses") val doses:String,
    @SerializedName("doses_novas") val doses_novas:String,
    @SerializedName("doses1") val doses1:String,
    @SerializedName("doses2") val doses2:String,
    @SerializedName("doses1_novas") val doses1_novas:String,
    @SerializedName("doses2_novas") val doses2_novas:String

    )
