package pt.ulusofona.deisi.a2020.cm.g4.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.entities.VaccinationData

@Dao
interface VaccineDataDAO {
    @Insert
    suspend fun insert(vaccinationData: VaccinationData)

    @Query("SELECT * FROM vaccinationdata")
    suspend fun getAllVaccination(): List<VaccinationData>

    @Query("SELECT * FROM vaccinationdata WHERE uuid = :uuid")
    suspend fun getById(uuid:String): VaccinationData

    @Query("SELECT * FROM vaccinationdata WHERE id = :id")
    suspend fun getByIdData(id:Long): VaccinationData

}

