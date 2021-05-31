package pt.ulusofona.deisi.a2020.cm.g4.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.dao.CovidDataDAO
import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.dao.TestDao
import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.entities.CovidData
import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.entities.Test

@Database(entities = arrayOf(CovidData::class), version = 1)
abstract class CovidBuddyDatabase : RoomDatabase(){

    abstract fun CovidDataDAO(): CovidDataDAO

    companion object{

        private var instance: CovidBuddyDatabase? = null

        fun getInstance(applicationContext: Context): CovidBuddyDatabase {
            synchronized(this){
                if(instance ==null){
                    instance = Room.databaseBuilder(
                        applicationContext,
                        CovidBuddyDatabase::class.java,
                        "covidbuddy_db"
                    ).build()
                }
                return instance as CovidBuddyDatabase
            }
        }
    }

}