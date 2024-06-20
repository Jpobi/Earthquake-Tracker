package com.example.earthquake_tracker

import Model.Feature
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var buttonMonth: Button
    private lateinit var buttonWeek: Button
    private lateinit var buttonDay: Button

    private lateinit var adapter: Adapter
    private var quakesList = mutableListOf<Feature>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView=findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = Adapter()
        adapter.submitList(quakesList)
        recyclerView.adapter = adapter

        buttonMonth=findViewById<Button?>(R.id.buttonMonth)
        buttonMonth.setOnClickListener{
            getQuakesMonth()
        }
        buttonWeek=findViewById(R.id.buttonWeek)
        buttonWeek.setOnClickListener{
            getQuakesWeek()
        }
        buttonDay=findViewById(R.id.buttonDay)
        buttonDay.setOnClickListener{
            getQuakesDay()
        }

    }

    private fun getQuakesMonth() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java).getSignificantLatestMonth()
            val response = call.body()

            runOnUiThread{
                if (call.isSuccessful) {
                    val quakes = response?.features ?: emptyList()
                    quakesList.clear()
                    quakesList.addAll(quakes)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun getQuakesDay() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java).getSignificantLatestDay()
            val response = call.body()

            runOnUiThread{
                if (call.isSuccessful) {
                    val quakes = response?.features ?: emptyList()
                    quakesList.clear()
                    quakesList.addAll(quakes)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun getQuakesWeek() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java).getAllWeek()
            val response = call.body()

            runOnUiThread{
                if (call.isSuccessful) {
                    val quakes = response?.features ?: emptyList()
                    quakesList.clear()
                    quakesList.addAll(quakes)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(MAIN_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        const val MAIN_URL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/"
    }
}