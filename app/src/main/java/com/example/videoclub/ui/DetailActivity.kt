package com.example.videoclub.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.videoclub.R
import com.example.videoclub.model.Movie
import com.example.videoclub.utils.loadImage
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity: AppCompatActivity() {

    private var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        retrieveMovie()
        renderUI()
    }


    private fun retrieveMovie(){
        movie = intent.getSerializableExtra("movie") as Movie?
    }

    //Imprimir por pantalla
    private fun renderUI(){
        detailName.text = movie?.name
        detailDescription.text = movie?.description
        movie?.cover?.let {
            mCover -> detailImage.loadImage(mCover)
        }

    }
}