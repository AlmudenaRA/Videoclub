package com.example.videoclub.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.videoclub.R
import com.example.videoclub.adapter.MoviesAdapter
import com.example.videoclub.model.Movie
import com.example.videoclub.utils.getJsonFromAssets
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MoviesAdapter(::onMovieCliked)
        recyclerview.adapter = adapter

        adapter.refreshList(getListFromJson())
    }

    /**
     * Función que devuelve el listado del json
     */
    private fun getListFromJson(): ArrayList<Movie>{
        val json = getJsonFromAssets("movies.json")
        val movieList = Gson().fromJson(json, Array<Movie>::class.java).toList()
        return ArrayList(movieList)
    }

    private fun onMovieCliked(movie: Movie){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("movie", movie) //pasar la pelicula
        startActivity(intent)
    }
}