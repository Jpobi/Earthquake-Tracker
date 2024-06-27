package models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName="terremoto")
data class Terremoto (
    @PrimaryKey
    val id: String,
    val title: String,
    val time : String,
    val mag: String,

    @ColumnInfo(name="long")
    val longitude: String,
    val latitude: String,
) : Parcelable