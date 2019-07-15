package osc.androiddevacademy.movieapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import osc.androiddevacademy.movieapp.R
import osc.androiddevacademy.movieapp.model.Movie

class TopMoviesAdapter: RecyclerView.Adapter<TopMoviesHolder>() {


    private val movies = mutableListOf<Movie>()

    fun setMovies(movies: List<Movie>){
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TopMoviesHolder, position: Int) {
        holder.bindItem(movies[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMoviesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_no_favourite, parent, false)
        return TopMoviesHolder(view)
    }

    override fun getItemCount() = movies.size


}