package models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Feature(
    val geometry: Geometry,
    val id: String,
    val properties: Properties,
    val type: String
) : Parcelable