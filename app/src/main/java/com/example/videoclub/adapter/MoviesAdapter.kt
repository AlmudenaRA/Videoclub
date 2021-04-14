package com.example.videoclub.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.videoclub.R
import com.example.videoclub.model.Movie
import com.example.videoclub.utils.inflate
import java.util.*
import kotlin.collections.ArrayList

class MoviesAdapter(private val listener: (Movie) -> Unit) : RecyclerView.Adapter<MoviesViewHolder>(){

    private val movieList = arrayListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = parent.inflate(R.layout.item_movie, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int = movieList.size //se le pasa la longitud total del estado


    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movieList[position] //posición del listado en la que está parado
        holder.bind(movie) //se le pasa esa posición al viewHolder que hemos creado y pinta la peli
        holder.itemView.setOnClickListener{
            listener(movie)
        }
    }

    //Rellena y actualiza el listado de películas
    fun refreshList(moveList: ArrayList<Movie>){
        this.movieList.addAll(moveList)
        notifyDataSetChanged()
    }

    fun filterByName(movies: List<Movie>){
        //limpiar lista
        movieList.clear()
        movieList.addAll(movies)
        notifyDataSetChanged()
    }

    fun orderByName(){
        val sortedList = movieList.sortedBy { it.name }
        movieList.clear()
        movieList.addAll(sortedList)
        notifyDataSetChanged()
    }

    fun insertObject(){
        val item = Movie(13, "Prueba", "Prueba", "https://image.tmdb.org/t/p/original//dRZpdpKLgN9nk57zggJCs1TjJb4.jpg", 1972)
        movieList.add(0, item)
        notifyItemInserted(0)
    }

    fun modifyObject(){
        movieList[0].name = "Prueba"
        notifyItemChanged(0)
    }

    fun deleteObject(){
        movieList.removeAt(0)
        notifyItemRemoved(0)

    }
}