package db

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import models.Terremoto


//entities = tablas de la base de datos
@Database(entities = [Terremoto::class], version = 1)
abstract class TerremotoDataBase: RoomDatabase() {
    abstract val terremotoDao: TerremotoDao
}

private lateinit var INSTANCE: TerremotoDataBase

fun getDataBase(context: Context): TerremotoDataBase {
    synchronized(TerremotoDataBase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                TerremotoDataBase::class.java,
                "terremoto_db"
            ).build()
        }
    }
    return INSTANCE
}