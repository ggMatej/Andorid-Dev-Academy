package osc.androiddevacademy.movieapp.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.ui.moviedetails.MovieDetailsFragment

class MoviePagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val moviesList = mutableListOf<Movie>()

    fun setMovies(moviesList: List<Movie>) {
        this.moviesList.clear()
        this.moviesList.addAll(moviesList)
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment =
        MovieDetailsFragment.getInstance(moviesList[position])

    override fun getCount(): Int = moviesList.size

}