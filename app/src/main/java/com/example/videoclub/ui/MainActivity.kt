package com.example.videoclub.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import com.example.videoclub.R
import com.example.videoclub.adapter.MoviesAdapter
import com.example.videoclub.model.Movie
import com.example.videoclub.utils.getJsonFromAssets
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: MoviesAdapter
    private val copyList = arrayListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MoviesAdapter(::onMovieCliked)
        recyclerview.adapter = adapter

        adapter.refreshList(getListFromJson())

        searchField.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            //Cuando pulsamos una letra
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    val filteredList = copyList.filter{
                        it.name.toLowerCase(Locale.getDefault()).contains(newText)
                    }
                    adapter.filterByName(filteredList)
                }
                return false
            }

            //Cuando se pulsa la lupa
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }
        })
    }

    /**
     * Función que devuelve el listado del json
     */
    private fun getListFromJson(): ArrayList<Movie>{
        val json = getJsonFromAssets("movies.json")
        val movieList = Gson().fromJson(json, Array<Movie>::class.java).toList()
        copyList.addAll(movieList) //añadir a la copia las peliculas que encuentre
        return ArrayList(movieList)
    }

    private fun onMovieCliked(movie: Movie){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("movie", movie) //pasar la pelicula
        startActivity(intent)
    }
}