package models

fun Feature.toTerremoto() = Terremoto(
    id = id,
    title = properties.title,
    mag = properties.mag.toString(),
    time = properties.time.toString(),
    longitude = geometry.coordinates[1].toString(),
    latitude = geometry.coordinates[0].toString()
)
