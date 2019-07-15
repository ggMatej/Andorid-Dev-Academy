package osc.androiddevacademy.movieapp.ui.favouriteandtopmovies.topmovies

import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.ui.base.BasePresenter

interface TopMoviesContract {

    interface View{

        fun onMoviesReceived(movies: ArrayList<Movie>)

        fun onGetMoviesFailed()

    }

    interface Presenter: BasePresenter<View> {

        fun getTopMovies()

    }
}