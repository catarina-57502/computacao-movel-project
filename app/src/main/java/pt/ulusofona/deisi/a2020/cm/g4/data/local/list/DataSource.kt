package pt.ulusofona.deisi.a2020.cm.g4.data.local.list
import pt.ulusofona.deisi.a2020.cm.g4.domain.test.Test
import kotlin.collections.ArrayList

class DataSource{

    companion object {
        var tests = ArrayList<Test>()
    }

    /*******************************DASHBOARD*************************************/

    var list_datas = mutableListOf<DataFile>(
        DataFile(
            "1-04-2021",
            824368,
            16887,
            781537,
            504,
            113,
            331422,
            117460,
            312537,
            29229,
            20880,
            4125,
            8715
        ),
        DataFile(
            "2-04-2021",
            825031,
            16890,
            782294,
            488,
            116,
            331604,
            117531,
            312799,
            29261,
            20926,
            4167,
            8743
        )
    )

    var confirmados_novos = 663
    var obitos = list_datas[1].obitos - list_datas[0].obitos
    var recuperados = list_datas[1].recuperados - list_datas[0].recuperados
    var internados = list_datas[1].internados - list_datas[0].internados
    var internados_uci = list_datas[1].internados_uci - list_datas[0].internados_uci

    var confirmados_arsnorte = list_datas[1].confirmados_arsnorte - list_datas[0].confirmados_arsnorte
    var confirmados_arscentro = list_datas[1].confirmados_arscentro - list_datas[0].confirmados_arscentro
    var confirmados_arslvt = list_datas[1].confirmados_arslvt - list_datas[0].confirmados_arslvt
    var confirmados_arsalentejo = list_datas[1].confirmados_arsalentejo - list_datas[0].confirmados_arsalentejo
    var confirmados_arsalgarve = list_datas[1].confirmados_arsalgarve - list_datas[0].confirmados_arsalgarve
    var confirmados_acores = list_datas[1].confirmados_acores - list_datas[0].confirmados_acores
    var confirmados_madeira = list_datas[1].confirmados_madeira - list_datas[0].confirmados_madeira

    var rt_nacional = 0.98

    /****************************************************************************/

    /*******************************VACINAÇÃO*************************************/
    var doses = 1672893
    var doses_novas = 30947
    var doses1 = 1148757
    var doses2 = 475866
    var doses1_perc = 11.3/100
    var doses2_perc = 4.6/100
    /****************************************************************************/

}