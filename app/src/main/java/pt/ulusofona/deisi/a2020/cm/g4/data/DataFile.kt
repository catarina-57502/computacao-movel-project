package pt.ulusofona.deisi.a2020.cm.g4.data

class DataFile {
    var data: String
    var confirmados: Int = 0
    var obitos: Int = 0
    var recuperados: Int = 0
    var internados: Int = 0
    var internados_uci: Int = 0
    var confirmados_arsnorte: Int = 0
    var confirmados_arscentro: Int = 0
    var confirmados_arslvt: Int = 0
    var confirmados_arsalentejo: Int = 0
    var confirmados_arsalgarve: Int = 0
    var confirmados_acores: Int = 0
    var confirmados_madeira: Int = 0

    constructor(data: String, confirmados: Int, obitos: Int, recuperados: Int, internados: Int, internados_uci: Int, confirmados_arsnorte: Int, confirmados_arscentro: Int, confirmados_arslvt: Int, confirmados_arsalentejo: Int, confirmados_arsalgarve: Int, confirmados_acores: Int, confirmados_madeira: Int) {
        this.data = data
        this.confirmados = confirmados
        this.obitos = obitos
        this.recuperados = recuperados
        this.internados = internados
        this.internados_uci = internados_uci
        this.confirmados_arsnorte = confirmados_arsnorte
        this.confirmados_arscentro = confirmados_arscentro
        this.confirmados_arslvt = confirmados_arslvt
        this.confirmados_arsalentejo = confirmados_arsalentejo
        this.confirmados_arsalgarve = confirmados_arsalgarve
        this.confirmados_acores = confirmados_acores
        this.confirmados_madeira = confirmados_madeira
    }
}