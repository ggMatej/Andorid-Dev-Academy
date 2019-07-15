package osc.androiddevacademy.movieapp.model

import com.google.gson.annotations.SerializedName

class MoviesResponse(@SerializedName("results") val movies: ArrayList<Movie>)