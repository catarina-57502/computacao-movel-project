package pt.ulusofona.deisi.a2020.cm.g4.data
import pt.ulusofona.deisi.a2020.cm.g4.Test

class DataSource{

    companion object {
        var tests = ArrayList<Test>()
    }

    /*******************************DASHBOARD*************************************/

    var confirmados = 823335
    var obitos = 16879
    var recuperados = 780322
    var internados = 517
    var internados_uci = 117

    var confirmados_0_9_f = 24
    var confirmados_0_9_m = 10
    var confirmados_10_19_f = 198
    var confirmados_10_19_m = 332
    var confirmados_20_29_f = 434
    var confirmados_20_29_m = 344
    var confirmados_30_39_f = 232
    var confirmados_30_39_m = 343
    var confirmados_40_49_f = 100
    var confirmados_40_49_m = 810
    var confirmados_50_59_f = 382
    var confirmados_50_59_m = 888
    var confirmados_60_69_f = 218
    var confirmados_60_69_m = 910
    var confirmados_70_79_f = 751
    var confirmados_70_79_m = 899
    var confirmados_80_plus_f = 70
    var confirmados_80_plus_m = 120

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