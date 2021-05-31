package ulht.cm.acalculator.data.remote.services


import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import pt.ulusofona.deisi.a2020.cm.g4.data.remote.requests.Data
import pt.ulusofona.deisi.a2020.cm.g4.data.remote.responses.DataResponseByDate
import retrofit2.http.GET
import retrofit2.http.Path
import ulht.cm.acalculator.data.remote.responses.DataResponse

interface DashboardService {

    @GET("Requests/get_last_update")
    suspend fun getLastUpdate () : Response<DataResponse>

    @GET("Requests/get_entry/{date}")
    suspend fun getEntryByDate (@Path("date") date: String) : Response<DataResponseByDate>
}