package osc.androiddevacademy.movieapp.ui.moviegrid

import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.ui.base.BasePresenter

interface MoviesGridFragmentContract {

    interface View{

        fun onMoviesReceived(movies: List<Movie>)

        fun onFavouriteMoviesReceived(movies: List<Movie>)

        fun onGetMoviesFailed()

    }

    interface Presenter: BasePresenter<View>{

        fun getPopularMovies()

        fun addFavourite(movie: Movie)

        fun deleteFavourite(movie: Movie)

        fun getFavouriteMovies()
    }
}