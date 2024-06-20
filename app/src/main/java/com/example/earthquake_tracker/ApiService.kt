package com.example.earthquake_tracker

import Model.Quakes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url


interface ApiService {
    @GET(value="significant_month.geojson")
    suspend fun getSignificantLatestMonth(): Response<Quakes>
    @GET(value="significant_day.geojson")
    suspend fun getSignificantLatestDay(): Response<Quakes>
    @GET(value="all_week.geojson")
    suspend fun getAllWeek(): Response<Quakes>
}