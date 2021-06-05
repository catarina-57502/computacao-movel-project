package pt.ulusofona.deisi.a2020.cm.g4.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.entities.CovidData

@Dao
interface CovidDataDAO {

    @Insert
    suspend fun insert(covidData: CovidData)

    @Query("SELECT * FROM covidData")
    suspend fun getAll(): List<CovidData>

    @Query("SELECT * FROM covidData WHERE uuid = :uuid")
    suspend fun getById(uuid:String): CovidData

    @Query("SELECT * FROM covidData WHERE data = :date")
    suspend fun getByDate(date:String): CovidData?

}