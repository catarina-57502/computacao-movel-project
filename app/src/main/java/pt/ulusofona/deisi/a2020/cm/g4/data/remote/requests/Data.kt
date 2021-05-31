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
    @SerializedName("Vacinados_Ac") val doses:String,
    @SerializedName("Vacinados") val doses_novas:String,
    @SerializedName("Inoculacao1_Ac") val doses1:String,
    @SerializedName("Inoculacao2_Ac") val doses2:String,
    @SerializedName("Inoculacao1") val doses1_novas:String,
    @SerializedName("Inoculacao2") val doses2_novas:String

    )
