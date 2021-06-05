package ulht.cm.acalculator.data.remote.responses

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("data") val data: String,
    @SerializedName("confirmados") val confirmados:Int,
    @SerializedName("confirmados_arsnorte") val confirmados_arsnorte:Int,
    @SerializedName("confirmados_arscentro") val confirmados_arscentro:Int,
    @SerializedName("confirmados_arslvt") val confirmados_arslvt:Int,
    @SerializedName("confirmados_arsalentejo") val confirmados_arsalentejo:Int,
    @SerializedName("confirmados_arsalgarve") val confirmados_arsalgarve:Int,
    @SerializedName("confirmados_acores") val confirmados_acores:Int,
    @SerializedName("confirmados_madeira") val confirmados_madeira:Int,
    @SerializedName("recuperados") val recuperados:Int,
    @SerializedName("obitos") val obitos:Int,
    @SerializedName("internados") val internados:Int,
    @SerializedName("internados_uci") val internados_uci:Int,
    @SerializedName("rt_nacional") val rt_nacional:Double,

    /***********************************Vacinas*******************************************/
    @SerializedName("doses") val doses:String,
    @SerializedName("doses_novas") val doses_novas:String,
    @SerializedName("doses1") val doses1:String,
    @SerializedName("doses2") val doses2:String,
    @SerializedName("doses1_novas") val doses1_novas:String,
    @SerializedName("doses2_novas") val doses2_novas:String
)