package pt.ulusofona.deisi.a2020.cm.g4.data.remote.services

import retrofit2.Response
import retrofit2.http.GET
import ulht.cm.acalculator.data.remote.responses.DataResponse

interface VaccinesService {

    @GET(".")
    suspend fun getLastUpdateVaccines () : Response<List<DataResponse>>

}