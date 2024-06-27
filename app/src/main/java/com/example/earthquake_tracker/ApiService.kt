package com.example.earthquake_tracker

import models.Quakes
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {
    @GET(value="significant_month.geojson")
    suspend fun getSignificantLatestMonth(): Response<Quakes>
    @GET(value="all_day.geojson")
    suspend fun getSignificantLatestDay(): Response<Quakes>
    @GET(value="all_week.geojson")
    suspend fun getAllWeek(): Response<Quakes>
}