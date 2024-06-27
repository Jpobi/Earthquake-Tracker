package com.example.earthquake_tracker

import android.content.Intent
import models.Feature
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import db.TerremotoDao
import db.TerremotoDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import models.Terremoto

class DetailActivity : AppCompatActivity() {
    private lateinit var titleView : TextView
    private lateinit var magnitudeView  : TextView
    private lateinit var longView  : TextView
    private lateinit var latView : TextView
    private lateinit var buttonBack: Button
    private lateinit var buttonFav: Button
    private lateinit var database: TerremotoDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        database=db.getDataBase(this)
        titleView=findViewById(R.id.titleViewDetail)
        magnitudeView=findViewById(R.id.magnitudeViewDetail)
        longView=findViewById(R.id.longitudeView)
        latView=findViewById(R.id.latitudeView)
        buttonBack=findViewById(R.id.buttonBack)
        buttonFav=findViewById(R.id.favoriteButton)

        val terremoto=intent.getParcelableExtra("quake") as? Terremoto

        titleView.text=terremoto?.title
        magnitudeView.text=terremoto?.mag
        longView.text= terremoto?.longitude
        latView.text= terremoto?.latitude

        buttonBack.setOnClickListener{
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        buttonFav.setOnClickListener{

            insertTerremotoInDatabase(terremoto)
        }

    }

    private fun insertTerremotoInDatabase(terremoto: Terremoto?) {
        CoroutineScope(Dispatchers.IO).launch {
            if (terremoto != null) {
                database.terremotoDao.insert(terremoto)
            }
        }

    }


}