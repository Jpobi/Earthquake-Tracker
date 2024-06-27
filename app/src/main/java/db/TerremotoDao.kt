package db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import models.Terremoto

@Dao
interface TerremotoDao {

    @Query("SELECT * FROM terremoto")
    fun getAllTerremotos(): MutableList<Terremoto>

    @Query("SELECT * FROM terremoto WHERE mag >= :mag")
    fun getTerremotosByMagnitude(mag : String) : MutableList<Terremoto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMultiple(terremotos: MutableList<Terremoto>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(terremoto: Terremoto)

    @Update
    fun update(terremoto: Terremoto)

    @Delete
    fun delete(terremoto: Terremoto)
}