package com.example.videoclub.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.videoclub.R
import com.example.videoclub.model.Movie
import com.example.videoclub.utils.getJsonFromAssets
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    /**
     * Función que devuelve el listado del json
     */
    private fun getListFromJson(): ArrayList<Movie>{
        val json = getJsonFromAssets("movies.json")
        val movieList = Gson().fromJson(json, Array<Movie>::class.java).toList()
        return ArrayList(movieList)
    }
}